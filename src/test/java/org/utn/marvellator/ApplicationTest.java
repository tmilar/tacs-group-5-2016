package org.utn.marvellator;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.utn.marvellator.config.MongoDbConfig;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.utn.marvellator.repository", "org.utn.marvellator.service", "org.utn.marvellator"})
@Import(MongoDbConfig.class)
public class ApplicationTest {

	@Test
	public void contextLoads() {
	}
}
