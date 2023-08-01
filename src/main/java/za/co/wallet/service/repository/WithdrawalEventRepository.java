package za.co.wallet.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import za.co.wallet.entities.Transaction;

@Component
public interface WithdrawalEventRepository extends JpaRepository<Transaction, Long>{
}
