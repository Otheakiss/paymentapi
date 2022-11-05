package com.brittanyweaver.payment.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("v1/users")
    public User createUser(@RequestBody User incomingUser) {
        return userService.createUser(incomingUser);
    }

    @GetMapping("v1/users/{user}")
    public User getUser(@PathVariable String user) {
        return userService.findUserByName(user);
    }
}
