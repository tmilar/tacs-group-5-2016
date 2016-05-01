package org.utn.marvellator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.utn.marvellator.config.MongoDbConfig;

@SpringBootApplication
@EnableAutoConfiguration
@Import(MongoDbConfig.class)
public class Application {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }
}
