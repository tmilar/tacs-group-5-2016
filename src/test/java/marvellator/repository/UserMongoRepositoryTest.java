package marvellator.repository;

/**
 * Created by Tomas on 4/19/2016.
 */

//import com.github.fakemongo.Fongo;
import com.foursquare.fongo.Fongo;

//import com.github.fakemongo.junit.FongoRule;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;

//import marvellator.ApplicationTestConfig;
import marvellator.model.User;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.*;

/**
 * Spring Data MongoDB Repository Unit testcase.
 *
 * @author ourownjava.com
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
//@SpringApplicationConfiguration(classes = ApplicationTestConfig.class)
public class UserMongoRepositoryTest {

//    @ClassRule
//    public static InMemoryMongoDb inMemoryMongoDb = newInMemoryMongoDbRule().build();

//    @Rule
//    public FongoRule fongoRule = new FongoRule();

    @Autowired
    private UserMongoRepository userMongoRepository;

    /**
     *
     *   nosql-unit requirement
     *
     */
    @Autowired private ApplicationContext applicationContext;

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("test");


    @Ignore
    @Test
    @ShouldMatchDataSet(location = "/testData/user-t1.json")
    public void shouldSaveUser(){
        userMongoRepository.save(createTestUser());
        System.out.println("User is " + createTestUser().toString());
    }

    @Ignore
    @Test
    @UsingDataSet(locations = {"/testData/user-t1.json"})
    public void shouldFindByUserId(){
        final List<User> users = userMongoRepository.findById("u1");
        System.out.println("Found "+ users.toString());
        assertNotNull(users);
        assertTrue(users.size() > 0);
        assertEquals("testUser1", users.get(0).getUserName());
    }

    @Test
    public void contextLoads(){

    }

    /**
     * Expected results are in "two-user.json" file
     */
    @Ignore
    @Test
    @ShouldMatchDataSet(location = "/two-user.json")
    public void shouldInsertUsers(){
//        this.userMongoRepository.insert(new User("Pepe", "123"));
//        this.userMongoRepository.insert(new User("Luis", "123"));
    }

    /**
     * Insert data from "five-person.json" and test countAllPersons method
     */
    @Ignore
    @Test
    @UsingDataSet(locations = {"/two-user.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void shouldCountUsers(){
        long total = this.userMongoRepository.count();

        assertEquals(2, total);
    }

    private User createTestUser(){
        final User user1 = new User();
        user1.setUserName("testUser1");
        user1.setPassword("123456");
        return user1;
    }

    @Configuration
    @EnableMongoRepositories
    @ComponentScan(basePackageClasses = { UserMongoRepository.class })
    @PropertySource("classpath:application.properties")
    static class MongoConfiguration extends AbstractMongoConfiguration {

        @Bean
        @Override
        protected String getDatabaseName() {
            return "test";
        }

        @Bean
        @Override
        public Mongo mongo() {
            return new Fongo("marvellator-test").getMongo();
        }

        @Override
        protected String getMappingBasePackage() {
            return "marvellator.model";
        }
    }

}
