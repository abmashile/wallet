package za.co.wallet.service.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.wallet.service.AccountService;
import za.co.wallet.service.WalletTransactionService;
import za.co.wallet.service.common.TransactionStatus;
import za.co.wallet.service.rest.reqresp.Account;
import za.co.wallet.service.rest.reqresp.TransactionRequest;
import za.co.wallet.service.rest.reqresp.TransactionResponse;

import java.math.BigDecimal;

@RestController
public class AccountController {
    private AccountService accountService;
    private RequestTransformer requestTransformer;

    @Autowired
    public AccountController(AccountService accountService, RequestTransformer requestTransformer){
        this.accountService = accountService;
        this.requestTransformer = requestTransformer;
    }


    @GetMapping("/account/{userId}")
    public Account findAccountByUser(@PathVariable Long userId)
    {
        Account account = this.requestTransformer.toResponse(accountService.getByUserId(userId));
        return Account.builder().id(account.getId()).balance(account.getBalance()).build();
    }
}
