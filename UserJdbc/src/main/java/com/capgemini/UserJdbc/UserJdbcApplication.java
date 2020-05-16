package com.capgemini.UserJdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserJdbcApplication.class, args);
	}

}
