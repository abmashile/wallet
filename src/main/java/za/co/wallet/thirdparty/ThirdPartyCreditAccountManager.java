package za.co.wallet.thirdparty;

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

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class ThirdPartyCreditAccountManager {

    public ThirdPartyCreditAccountManager()
    {}

    public boolean mimic(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
