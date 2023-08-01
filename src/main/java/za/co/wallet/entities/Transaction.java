package za.co.wallet.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import za.co.wallet.service.common.TransactionStatus;
import za.co.wallet.service.common.TransactionType;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private TransactionType transactionType;
    @NonNull
    private BigDecimal amount;
    private BigDecimal newBalance;
    private BigDecimal oldBalance;
    @NonNull
    private TransactionStatus status;
    @ManyToOne
    private Account account;
}
