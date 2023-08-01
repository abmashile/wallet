package za.co.wallet.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
@EnableJpaAuditing
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal balance;
    @OneToOne(mappedBy = "account")
    private User user;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transaction;

}
