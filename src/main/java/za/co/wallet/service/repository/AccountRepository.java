package za.co.wallet.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import za.co.wallet.entities.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Long>{

    public Account findByUserId(Long userId);
}
