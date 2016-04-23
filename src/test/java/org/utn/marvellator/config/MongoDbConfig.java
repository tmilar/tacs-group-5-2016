package org.utn.marvellator.config;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "test-db";
    }

    @Override
    public Mongo mongo() {
        return new Fongo("test").getMongo();
    }

    @Override
    protected String getMappingBasePackage() {
        return "org.utn.marvellator";
    }
}
