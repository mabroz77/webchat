package org.sda.webchat.repository;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sda.webchat.dto.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void findByDate() {
        Date date = new Date();
        Message message = new Message(new ObjectId(), "fromTest", "toNobody", "nasza wiadomosc testowa", date);
        List<Message> messageList = new ArrayList<>();
        messageList.add(message);
        messageRepository.save(message);
        List<Message> msgFromDB = messageRepository.findByDate(date);
        Assert.assertEquals(msgFromDB, messageList);

    }
}