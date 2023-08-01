package za.co.wallet.service.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.wallet.entities.Transaction;
import za.co.wallet.service.WalletTransactionService;
import za.co.wallet.service.common.TransactionStatus;
import za.co.wallet.service.rest.reqresp.TransactionResponse;
import za.co.wallet.service.rest.reqresp.Account;
import za.co.wallet.service.rest.reqresp.TransactionRequest;

import java.math.BigDecimal;

@RestController
public class TransactionController {
    private WalletTransactionService walletTransactionService;
    private RequestTransformer requestTransformer;

    @Autowired
    public TransactionController(WalletTransactionService walletTransactionService, RequestTransformer requestTransformer){
        this.walletTransactionService = walletTransactionService;
        this.requestTransformer = requestTransformer;
    }
    @PostMapping("/transact")
    public TransactionResponse transact(@Valid @RequestBody TransactionRequest request)
    {
        Transaction trn = this.walletTransactionService.initiateTransaction(request.getUserId(), request.getTransactionType(), request.getAmount());
        return TransactionResponse.builder().transactionType(request.getTransactionType())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status(trn.getStatus())
                .balance(trn.getNewBalance())
                .build();

    }

}
