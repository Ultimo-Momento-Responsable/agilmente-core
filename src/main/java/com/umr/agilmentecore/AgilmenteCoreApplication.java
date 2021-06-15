package com.umr.agilmentecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.umr.agilmentecore.Persistence")
@SpringBootApplication
public class AgilmenteCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgilmenteCoreApplication.class, args);
	}

}
