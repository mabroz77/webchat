package org.sda.webchat.repository;

import org.bson.types.ObjectId;
import org.sda.webchat.dto.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message, ObjectId> {

    List<Message> findAll();

    Optional<Message> findById(ObjectId objectId);

    List<Message> findByDate(Date date);

    List<Message> findByDateGreaterThan(Date date);

}
