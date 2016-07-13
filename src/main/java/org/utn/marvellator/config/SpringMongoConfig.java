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
@EnableMongoRepositories("org.utn.marvellator.model")
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private Integer mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.username}")
    private String  userName;

    @Value("${spring.data.mongodb.password}")
    private String  password;

    @Override
    @Bean
    public Mongo mongo() throws Exception {

        ServerAddress serverAddress = new ServerAddress(mongoHost, mongoPort);

        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(MongoCredential.createScramSha1Credential(
                userName,
                dbName,
                password.toCharArray()
        ));

        MongoClientOptions options = new MongoClientOptions.Builder()
                .build();

        return new MongoClient(serverAddress, credentials, options);
    }

    @Override
    public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
        return super.mongoMappingContext();
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

}
