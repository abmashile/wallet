package za.co.wallet.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import za.co.wallet.entities.Transaction;
import za.co.wallet.service.common.TransactionStatus;
import za.co.wallet.thirdparty.ThirdPartyCreditAccountManager;

@Service
@Slf4j
public class WalletTransactionProcessor {
    private WalletTransactionService walletTransactionService;
    @Autowired
    private ThirdPartyCreditAccountManager creditAccountManager;

    @Autowired
    public WalletTransactionProcessor(WalletTransactionService walletTransactionService)
    {
        this.walletTransactionService = walletTransactionService;
    }

    @RabbitListener(queues = {"${async.transaction.queue}"})
    public void process(@Payload String walletTransactionMessage){
        Gson gson = new Gson();
        log.info("Message received : {} ", gson.toJson(walletTransactionMessage));
        Transaction transaction = gson.fromJson(walletTransactionMessage, Transaction.class);

        walletTransactionService.transact(transaction.getAccount().getUser().getId(), transaction.getTransactionType(), transaction.getAmount(), TransactionStatus.ACTUALISING);
        boolean success = creditAccountManager.mimic();
        if(success){
           // walletTransactionService.updateStatus(transaction.getId(), TransactionStatus.DONE);
        }
    }


}
