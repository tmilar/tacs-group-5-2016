package org.utn.marvellator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import org.utn.marvellator.config.*;

@SpringBootApplication
@EnableAutoConfiguration
@Import(SpringSecurityConfig.class)
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }
}
