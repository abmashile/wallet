package za.co.wallet.service.rest.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wallet.service.rest.reqresp.Account;
import za.co.wallet.service.rest.reqresp.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RequestTransformer {
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    public RequestTransformer(DozerBeanMapper dozerBeanMapper){
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public User toResponse(za.co.wallet.entities.User user){
        if(Objects.nonNull(user)) {
            return dozerBeanMapper.map(user, User.class);
        }
        return null;
    }

    public Account toResponse(za.co.wallet.entities.Account account) {
        if (Objects.nonNull(account)) {
            return dozerBeanMapper.map(account, Account.class);
        }
        return null;
    }


    public List<Account> toResponse(List<za.co.wallet.entities.Account> accounts){
        return accounts.stream().map(p-> toResponse(p)).collect(Collectors.toList());
    }
}