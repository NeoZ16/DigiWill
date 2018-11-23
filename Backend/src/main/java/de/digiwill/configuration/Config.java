package de.digiwill.configuration;


import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@PropertySource("classpath:secrets-${envTarget}.properties")
public class Config {
    @Autowired
    Environment env;

    Logger logger = LoggerFactory.getLogger(Config.class);
    @Bean("emailconfig")
    public EmailConfig getEmailConfig(){
       return new EmailConfig(env.getProperty("mail.host"),
               env.getProperty("mail.port"),
               env.getProperty("mail.user"),
               env.getProperty("mail.password"));
    }

    @Bean("mongodbfactory")
    MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(env.getProperty("database.host"), Integer.parseInt(env.getProperty("database.port"))),
                env.getProperty("database.name"));
    }

}