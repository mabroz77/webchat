package org.sda.webchat.dto.message;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "message_collection")
public class Message {

    @Id
    private ObjectId id;
    private String from;
    private String to;
    private String text;
    private Date date;

}
