package org.sda.webchat.repository;

import org.bson.types.ObjectId;
import org.sda.webchat.dto.user.User;
import org.sda.webchat.dto.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class UserDetailsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public UserDetailsRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void update(ObjectId id, UserStatus userStatus){
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("userStatus", userStatus);

        User user = mongoTemplate.findAndModify(query,update,
                new FindAndModifyOptions().returnNew(true), User.class);
    }
}
