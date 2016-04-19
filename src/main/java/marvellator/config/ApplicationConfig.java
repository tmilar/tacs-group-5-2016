package marvellator.config;

import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import marvellator.Application;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

/**
 * Created by Tomas on 4/19/2016.
 */
@Configuration
@EnableMongoRepositories
@ComponentScan(basePackageClasses = {Application.class})
@PropertySource("classpath:application.properties")
public class ApplicationConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "marvellator";
    }

    @Override
    public Mongo mongo() throws Exception {
        /**
         *
         * this is for a single db
         */

        // return new Mongo();

        /**
         *
         * This is for a relset of db's
         */
        return new Mongo(new ArrayList<ServerAddress>() {{
            add(new ServerAddress("127.0.0.1", 27017));
            add(new ServerAddress("127.0.0.1", 27027));
            add(new ServerAddress("127.0.0.1", 27037));
        }});
    }

    @Override
    protected String getMappingBasePackage() {
        return "marvellator.model";
    }

}
