package org.sda.webchat.repository;

import org.bson.types.ObjectId;
import org.sda.webchat.dto.user.User;
import org.sda.webchat.dto.user.UserStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,ObjectId> {

    List<User> findAll();

    List<User> getAllByUserStatus(UserStatus userStatus);

    Optional<User> findById(ObjectId id);

    User findUserByLoginAndPassword(String login, String Password);

    User findUserByLogin(String login);

    List<User> findUsersByUserStatus(UserStatus userStatus);
}
