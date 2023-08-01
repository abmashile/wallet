package za.co.wallet.service;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wallet.entities.Account;
import za.co.wallet.entities.Transaction;
import za.co.wallet.entities.User;
import za.co.wallet.service.common.TransactionStatus;
import za.co.wallet.service.common.TransactionType;
import za.co.wallet.service.repository.AccountRepository;
import za.co.wallet.service.repository.WithdrawalEventRepository;
import za.co.wallet.service.rest.reqresp.TransactionResponse;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class WalletTransactionService {
    private UserService userService;
    private AccountRepository accountRepository;
    private WithdrawalEventRepository withdrawalEventRepository;

    private WalletTransactionInitiator transactionInitiator;
    private WalletTransactionValidator walletTransactionValidator;

    @Autowired
    public WalletTransactionService(UserService userService, AccountRepository productRepository, WalletTransactionValidator walletTransactionValidator,
                                    WithdrawalEventRepository withdrawalEventRepository,
                                    WalletTransactionInitiator asyncWithdrawalInitiator){
        this.userService = userService;
        this.accountRepository = productRepository;
        this.walletTransactionValidator = walletTransactionValidator;
        this.withdrawalEventRepository = withdrawalEventRepository;
        this.transactionInitiator = asyncWithdrawalInitiator;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Transaction initiateTransaction(Long userId, TransactionType transactionType, BigDecimal amount){
        log.info("User({}) is initiating transaction: transactionType = {}, amount = {} ", userId, transactionType, amount);

        TransactionStatus status = TransactionStatus.INITIATED;

        User user = this.userService.getById(userId);
        Account account = user.getAccount();


        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .account(Account.builder()
                        .balance(account.getBalance())
                        .id(account.getId())
                        .user(User.builder()
                                .id(user.getId())
                                .build())
                        .build())
                .amount(amount)
                .status(status)
                .build();

        transactionInitiator.initiateTransaction(transaction);
        return transaction;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public TransactionStatus transact(Long userId, TransactionType transactionType, BigDecimal amount, TransactionStatus status){
        log.info("User({}) is transacting, TransactionType = {}, Amount = R{}, status = {}", userId, transactionType.name(), amount, status.name());
        User user = this.userService.getById(userId);
        if(Objects.nonNull(user)) {
            Account account = user.getAccount();

            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = getBalance(amount, account.getBalance(), transactionType);
            account.setBalance(newBalance);

            Transaction transaction = Transaction.builder()
                    .transactionType(transactionType)
                    .account(account)
                    .amount(amount)
                    .newBalance(newBalance)
                    .oldBalance(oldBalance)
                    .status(status)
                    .build();

            account.getTransaction().add(transaction);
            accountRepository.save(account);


        }

        return TransactionStatus.DONE;
    }

    public boolean updateStatus(long transactionId, TransactionStatus status){
        log.info("Transaction Update. id = {}, status = {}", transactionId, status);
        return true;
    }


    private BigDecimal getBalance(BigDecimal amount, BigDecimal balance, TransactionType transactionType){
        switch (transactionType){
            case CREDIT ->   balance.add(amount);
            case DEBIT ->  balance.subtract(amount);
        }
        return amount;
    }
}
