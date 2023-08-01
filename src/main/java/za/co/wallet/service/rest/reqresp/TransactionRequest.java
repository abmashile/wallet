package za.co.wallet.service.rest.reqresp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.NonNull;
import za.co.wallet.service.common.TransactionType;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@ToString
public class TransactionRequest {
    @NonNull
    private Long userId;
    private TransactionType transactionType;
    @NonNull
    private BigDecimal amount;
}
