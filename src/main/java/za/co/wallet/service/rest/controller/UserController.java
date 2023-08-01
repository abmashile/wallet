package za.co.wallet.service.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.wallet.service.UserService;
import za.co.wallet.service.rest.reqresp.TransactionRequest;
import za.co.wallet.service.rest.reqresp.User;

import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RequestTransformer requestTransformer;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id)
    {
        return requestTransformer.toResponse(userService.getById(id));
    }



    @PostMapping("/user/create")
    public User create(@RequestBody User request)
    {
        User user = requestTransformer.toResponse(this.userService.create(request.getUsername(), request.getPassword(), request.getEmail()));
        return user;
    }
}
