package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.services.SimpleMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

	@Bean
	String message() {
		System.out.println("...creating 'message' bean");
		return "Hello, Spring!";
	}
	@Bean
	String uppercaseMessage(SimpleMessageService sms) {
		System.out.println("...creating 'uppercaseMessage' bean");
		return sms.uppercase();
	}
	@Bean
	String lowercaseMessage(SimpleMessageService sms) {
		System.out.println("...creating 'lowercaseMessage' bean");
		return sms.lowercase();
	}
}
