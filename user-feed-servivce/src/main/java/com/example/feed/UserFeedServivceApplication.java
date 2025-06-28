package com.example.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UserFeedServivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFeedServivceApplication.class, args);
	}

}
