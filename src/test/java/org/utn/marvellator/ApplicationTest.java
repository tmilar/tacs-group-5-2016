package org.utn.marvellator;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.utn.marvellator.config.MongoDbConfig;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.utn.marvellator.repository", "org.utn.marvellator.service"})
@Import(MongoDbConfig.class)
public class ApplicationTest {

	@Test
	public void contextLoads() {
	}
}
