package com.ext.subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.ext.subject.repository")
public class ExtensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtensionApplication.class, args);
	}

}
