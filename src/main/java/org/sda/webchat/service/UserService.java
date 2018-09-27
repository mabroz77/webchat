package org.sda.webchat.service;

import org.sda.webchat.dto.user.User;
import org.sda.webchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    /**
     * method to find and back true if user exist in DB and password is matched
     */

    public boolean ifThatUserExistAndPasswordIsOk(User user) {
        return userRepository.findAll()
                .stream()
                .filter(user1 -> user1.getLogin().equals(user.getLogin())
                        && user1.getPassword().equals(user.getPassword()))
                .findFirst().isPresent();

    }

    /**
     * method to back true if user login is exist in DB
     */

    public boolean ifThatUserLoginExistInDB(User user) {
        return userRepository.findAll().stream()
                .filter(user1 -> user1.getLogin().equals(user.getLogin()))
                .findFirst().isPresent();

    }

}
