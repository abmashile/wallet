package za.co.wallet.service.rest.reqresp;

import lombok.Builder;
import lombok.Data;
import za.co.wallet.service.common.TransactionStatus;
import za.co.wallet.service.common.TransactionType;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionResponse {
    private Long userId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private TransactionStatus status;
}
