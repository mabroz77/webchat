package org.sda.webchat.controllers;

import org.sda.webchat.dto.message.Message;
import org.sda.webchat.dto.user.User;
import org.sda.webchat.dto.user.UserStatus;
import org.sda.webchat.repository.MessageRepository;
import org.sda.webchat.repository.UserDetailsRepository;
import org.sda.webchat.repository.UserRepository;
import org.sda.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserService userService;
    private UserRepository userRepository;
    private UserDetailsRepository userDetailsRepository;
    private MessageRepository messageRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @RequestMapping("/index")
    public String getIndexPage(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @RequestMapping("/chat")
    public String getChatPage(User user, Message message, Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("messages", messageRepository.findAll());
        return "chat";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute User user, Model model, HttpServletResponse response) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("messages", messageRepository.findAll());
        if (userService.ifThatUserExistAndPasswordIsOk(user)) {
            if (user.getLogin().equals("") || user.getPassword().equals("")) {
                return "register";
            } else {
               //do when pass and nick is ok
                Cookie cookie = new Cookie("login", user.getLogin());
                user.setUserStatus(UserStatus.online);
                userDetailsRepository.update(userRepository
                        .findUserByLoginAndPassword(user.getLogin(), user.getPassword()).getId(),
                        UserStatus.online);
                response.addCookie(cookie);
                return "redirect:/chat";
            }
        } else return "register";
    }


    @RequestMapping(value = "/saveMessage", method = RequestMethod.POST)
    public String saveMessageSendToDB(User user, @ModelAttribute Message message, Model model, HttpServletRequest request) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("messages", messageRepository.findAll());

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")) {
                message.setFrom(cookie.getValue());
            }
        }
        message.setDate(new Date());
        messageRepository.save(message);
        return "redirect:/chat";
    }
}
