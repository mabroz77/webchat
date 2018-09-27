package org.sda.webchat.controllers;

import org.sda.webchat.dto.user.User;
import org.sda.webchat.dto.user.UserStatus;
import org.sda.webchat.repository.UserDetailsRepository;
import org.sda.webchat.repository.UserRepository;
import org.sda.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ComponentScan(value = "/repository")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    public void setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
//        this.userDetailsRepository = userDetailsRepository;
//    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(@ModelAttribute User user, Model model) {
        return "register";
    }

    @RequestMapping(value = "/registeredUserExistInDB", method = RequestMethod.GET)
    public String getRegisteredUserExistInDBPage(Model model) {
        return "registeredUserExistInDB";
    }


    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUserSendToDB(@ModelAttribute User user, Model model) {
        if (userService.ifThatUserLoginExistInDB(user)) {
            System.out.println("taki uzytkownik istnieje");
            return "registeredUserExistInDB";
        } else {
            user.setUserStatus(UserStatus.offline);
            userRepository.save(user);
            return "index";
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUserOnline(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userTable";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")) {
                user.setLogin(cookie.getValue());
            }
        }
        userDetailsRepository.update(userRepository
                        .findUserByLogin(user.getLogin()).getId(),
                UserStatus.offline);
        return "redirect:/login";
    }
}
