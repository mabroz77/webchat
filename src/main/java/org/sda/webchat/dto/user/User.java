package org.sda.webchat.dto.user;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "user_collection")
public class User {

    @Id
    private ObjectId id;
    private String login;
    private String password;
    private UserStatus userStatus; //enum status
}
