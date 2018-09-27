package org.sda.webchat.controllers;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sda.webchat.dto.message.Message;
import org.sda.webchat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void shouldSaveMessageSendToDB() {

        Date date = new Date();
        ObjectId objectId = new ObjectId();
        Message message = new Message(objectId, "fromTest", "toNobody", "nasza wiadomosc testowa", date);
        messageRepository.save(message);
        Optional<Message> messageFromDB = messageRepository.findById(objectId);
        Assert.assertEquals(date, messageFromDB.get().getDate());

    }
}