package org.sda.webchat;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ConfigSpringMongo {

    private static String host = "localhost";
    private static Integer port = 27017;
    private static String dbName = "chatAppDB";

    @Bean
    public MongoClient mongo(){
        return new MongoClient(host);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongo(),dbName);
    }
}
