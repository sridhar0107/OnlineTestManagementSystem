package com.capgemini.UserLogin.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Exceptions.UserExceptions;
import com.capgemini.UserLogin.Service.ServiceMain;
import com.capgemini.UserLogin.entity.Test;
import com.capgemini.UserLogin.entity.User;



@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class Controller {

	
	@Autowired
	private ServiceMain service;
	
	//user login method
	@GetMapping("/UserLogin/{userId},{password}")
	public String userLogin(@PathVariable("userId") String userId,@PathVariable("password") String password) {
		Optional<User> userDetails = service.userLogin(userId,password);
		
		return userDetails.toString();
	}
	
	//Retrieving test details of particular User
	@GetMapping("/UserTest/{userId}")
	public Test userTest(@PathVariable("userId") String userId) {
		Test testDetails = service.userTest(userId);
     if (testDetails==null) {
			
			throw new UserExceptions("Test is not assigned to the User");
		}
     else
     {
		return testDetails;
     }
	}
	
	@ExceptionHandler(UserExceptions.class)
	public ResponseEntity<String> userNotFound(UserExceptions e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}

