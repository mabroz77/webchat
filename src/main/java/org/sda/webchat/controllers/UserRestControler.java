package org.sda.webchat.controllers;

import lombok.AllArgsConstructor;
import org.sda.webchat.dto.user.User;
import org.sda.webchat.dto.user.UserStatus;
import org.sda.webchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@EnableAutoConfiguration
public class UserRestControler {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/userOnline", method = RequestMethod.GET)
    public List<User> getUserOnline(Model model) {
        return userRepository.findUsersByUserStatus(UserStatus.online);
    }
}
