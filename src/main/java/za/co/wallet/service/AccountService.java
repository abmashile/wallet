package za.co.wallet.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wallet.entities.Account;
import za.co.wallet.entities.User;
import za.co.wallet.service.repository.AccountRepository;
import za.co.wallet.service.repository.UserRepository;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public Account getByUserId(long id){
        return this.accountRepository.findByUserId(id);
    }
}

