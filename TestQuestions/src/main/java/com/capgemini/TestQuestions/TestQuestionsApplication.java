package com.capgemini.TestQuestions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestQuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestQuestionsApplication.class, args);
	}

}
