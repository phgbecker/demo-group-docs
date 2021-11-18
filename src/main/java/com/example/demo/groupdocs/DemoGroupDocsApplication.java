package com.example.demo.groupdocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemoGroupDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGroupDocsApplication.class, args);
	}

}
