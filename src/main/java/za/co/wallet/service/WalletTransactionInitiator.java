package za.co.wallet.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wallet.entities.Transaction;

@Service
@Slf4j
public class WalletTransactionInitiator {
    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate template;
    public void initiateTransaction(Transaction message){
        String strMessage = new Gson().toJson(message);
        template.convertAndSend(queue.getName(), strMessage);
        log.info("Transaction Initiation Message sent for processing: {}", strMessage);
    }
}
