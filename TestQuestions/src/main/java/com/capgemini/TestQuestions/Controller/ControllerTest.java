package com.capgemini.TestQuestions.Controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.TestQuestions.Service.ServiceTest;
import com.capgemini.TestQuestions.entity.Question;
import com.capgemini.TestQuestions.entity.Test;
import com.capgemini.exceptions.IdNotFoundException;


@RestController
@RequestMapping("/testquestions")

@CrossOrigin("http://localhost:4200")
public class ControllerTest {

	
	
	@Autowired
	ServiceTest service;
	
	//Adding question to test with particular testId
	@PostMapping("/addQuestion/{testId}")
	public ResponseEntity<String> addQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Test testDetails = service.addQuestion(testId,question);
		if (testDetails == null) {

			throw new IdNotFoundException("Question not added");

		} else {
			return new ResponseEntity<String>("Question added successfully", new HttpHeaders(), HttpStatus.OK);
		}
		
	}
	
	//Calculating total marks in the test
	@PostMapping("/calculateTotalMarks")
	public Test calculateTotalMarks(@RequestBody Test test) {
		Test testDetails = service.calculateTotalMarks(test);
		if (testDetails == null) {

			throw new IdNotFoundException("Test details not found");
		}
		else
		{
		return testDetails;
		}
	}
	

	//Update question in a test with particular testId
	@PostMapping("/updateQuestion/{testId}")
	public ResponseEntity<String> updateQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Question questionDetails = service.updateQuestion(testId,question);
		if (questionDetails == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided testId does not exist");
		
		} else {
			return new ResponseEntity<String>("Question updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	

   //Delete question in a Test with particular testId
	@PostMapping("/deleteQuestion/{testId}")
	private ResponseEntity<String> deleteQuestion(@PathVariable("testId") BigInteger testId,@RequestBody Question question) {
		Boolean status = service.deleteQuestion(testId,question);
		if (status == false) {
			throw new IdNotFoundException("Delete operation is unsuccessful");
		
		} else {
			return new ResponseEntity<String>("Delete operation is successful", new HttpHeaders(), HttpStatus.OK);
		
	}
	}
	
	//Exception Handling
		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<String> userNotFound(IdNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	
}
