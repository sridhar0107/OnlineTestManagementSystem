package com.capgemini.UserJdbc.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Exceptions.UserExceptions;
import com.capgemini.UserJdbc.Service.ServiceMain;
import com.capgemini.UserJdbc.entity.User;


@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class Controller {

	@Autowired
	private ServiceMain service;
	
	//user registration method
	@PostMapping("/UserRegistration")
	public ResponseEntity<String> userRegistration(@RequestBody User user) {
		User userDetails = service.userRegistration(user);
		if (userDetails == null) {
			return new ResponseEntity<String>("userId already Exist", new HttpHeaders(), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("User created successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	
	

	// Updating User password
	@GetMapping("/PasswordRecovery/{userId},{password},{rePassword}")
	public ResponseEntity<String> passwordRecovery(@PathVariable("userId") String userId,@PathVariable("password") String password,@PathVariable("rePassword") String rePassword) {
		int status = service.passwordRecovery(userId,password,rePassword);
		if (status == 0) {
			
			throw new UserExceptions("Update Operation Unsuccessful,Provided UserId does not exist");
		
		} else {
			return new ResponseEntity<String>("Password updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	

	@ExceptionHandler(UserExceptions.class)
	public ResponseEntity<String> userNotFound(UserExceptions e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
