package org.sda.webchat.controllers;

import lombok.AllArgsConstructor;
import org.sda.webchat.dto.message.Message;
import org.sda.webchat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@EnableAutoConfiguration
public class MessageRestController {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(value = "/messageList", method = RequestMethod.GET)
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @RequestMapping(value = "/messageLast", method = RequestMethod.GET)
    public @ResponseBody List<Message> getLast(@RequestParam Long date) {
        return messageRepository.findByDateGreaterThan(new Date(date));
    }

    private Date parseDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
}
