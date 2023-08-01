package za.co.wallet.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wallet.entities.Account;
import za.co.wallet.entities.User;
import za.co.wallet.service.repository.UserRepository;

import java.math.BigDecimal;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository investorRepository){
        this.userRepository = investorRepository;
    }
    public User getById(long id){
        return this.userRepository.findById(id).orElse(null);
    }

    public User create(String username, String password, String email){
        User user = User.builder().username(username).password(password).email(email).build();
        Account account = Account.builder()
                .user(user)
                .balance(BigDecimal.valueOf(0))
                .build();
        user.setAccount(account);
        user = this.userRepository.save(user);
        log.info("User Created: {}", user.getUsername());
        return user;
    }


}

