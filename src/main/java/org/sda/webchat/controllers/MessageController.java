package org.sda.webchat.controllers;

import org.sda.webchat.dto.message.Message;
import org.sda.webchat.repository.MessageDetailsRepository;
import org.sda.webchat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;


@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private MessageDetailsRepository messageDetailsRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setMessageDetailsRepository(MessageDetailsRepository messageDetailsRepository) {
        this.messageDetailsRepository = messageDetailsRepository;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }

}
