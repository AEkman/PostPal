package com.andreasekman.com.postpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class PostPalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostPalApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager() {
		GuavaCacheManager cacheManager = new GuavaCacheManager("notes");
		return cacheManager;
	}
}
