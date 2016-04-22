package org.utn.marvellator;

import com.github.fakemongo.Fongo;
import com.github.fakemongo.junit.FongoRule;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.utn.marvellator.repository", "org.utn.marvellator.service"})
public class MarvellatorApplicationTestConfig {

	@Rule
	public FongoRule fongoRule = new FongoRule();

	@Configuration
//	@EnableMongoRepositories
	static class MongoConfiguration extends AbstractMongoConfiguration {

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

	@Test
	public void contextLoads() {
	}
}
