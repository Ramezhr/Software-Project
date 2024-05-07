package com.example.feedbackservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.feedbackservice.service.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FeedbackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackServiceApplication.class, args);
	}

}
