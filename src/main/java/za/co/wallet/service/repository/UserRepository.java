package za.co.wallet.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import za.co.wallet.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>{

}
