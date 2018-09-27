package org.sda.webchat.repository;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sda.webchat.dto.user.User;
import org.sda.webchat.dto.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailsRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Test
    public void shouldUpdate() {
        ObjectId objectId = new ObjectId();
        User user = new User(objectId, "testLogin", "testPass", UserStatus.offline);
        userRepository.save(user);
        userDetailsRepository.update(objectId, UserStatus.online);
        Assert.assertEquals(userRepository.findById(objectId).get().getUserStatus(), UserStatus.online);

    }
}