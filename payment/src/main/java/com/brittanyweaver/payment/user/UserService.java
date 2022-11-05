package com.brittanyweaver.payment.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User incomingUser) {
        User user = new User();
        user.setEmail(incomingUser.getEmail());
        user.setName(incomingUser.getName());
        return userRepository.save(user);
    }

    public User findUserByName(String user) {
        Optional<User> maybeUser =  userRepository.getByName(user);
        if(maybeUser.isPresent()) {
            return maybeUser.get();
        }
        throw new UserNotFoundException();
    }
}
