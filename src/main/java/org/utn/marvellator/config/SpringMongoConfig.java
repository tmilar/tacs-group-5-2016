package org.utn.marvellator.config;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("openshift")
@EnableMongoRepositories("org.utn.marvellator")
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbURI;

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        String mongoDbURI = "mongodb://admin:gLQki2_Tntbu@127.5.25.2:27017/"; //hardcoding , seems to be injected wrongly...\
        
        System.out.println("MondodbURI is: " + mongoDbURI);
        return new MongoClient(new MongoClientURI(mongoDbURI));
    }

//    @Override
//    public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
//        return super.mongoMappingContext();
//    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

}
