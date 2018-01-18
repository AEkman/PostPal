package com.andreasekman.com.postpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PostPalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostPalApplication.class, args);
	}
}
