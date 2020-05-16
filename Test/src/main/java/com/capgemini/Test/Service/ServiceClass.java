package com.capgemini.Test.Service;

import java.math.BigInteger;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.Test.Dao.DaoClass;
import com.capgemini.Test.Dao.DaoClass1;
import com.capgemini.Test.entity.Question;
import com.capgemini.Test.entity.Test;
import com.capgemini.Test.entity.User;

@Service
@Transactional
public class ServiceClass {

	@Autowired
	DaoClass dao;
	
	@Autowired
	DaoClass1 dao1;
	
	
	//Adding Test 
	public Test addTest(Test test)
	{
		return dao.save(test);
		
	}
	
	
   //Retrieving all Test details from database
	public List<Test> testDetails() {
		return dao.findAll();
	}
 
	//Update Test
	public Test updateTest(BigInteger testId, Test test)
	{
		if(dao.existsById(testId))
		{
			Test t=dao.getOne(testId);
			test.setTestQuestions(t.getTestQuestions());
			 return dao.save(test);
		}
		else
		{
			return null;
		}
	}
	
	//Delete Test
	public String deleteTest(BigInteger testId)
	{
		if(dao.existsById(testId))
		{
		  dao.deleteById(testId);
		  return "deleted successfully";
		}
		else
		{
			return null;
		}
	}

	//Retrieving Test details with particular testId
	 public Optional<Test> getTestById(BigInteger testId) {
			
			return dao.findById(testId);
		}

	 //Retrieving Question details with particular testId
	public Set<Question> getQuestionById(BigInteger testId) {
		Test t=dao.getOne(testId);
		return t.getTestQuestions();
	}

	
	//Assigning Test to particular User
	public User assignTest(BigInteger testId, String userId) {
		if(dao.existsById(testId))
		{
			Test t=dao.getOne(testId);
			User u=dao1.getOne(userId);
			t.setUser(u);
			u.setUserTest(t);
			return dao1.save(u);
			
			
		}
		else
		{
		return null;
	}
	}
		
	
	


}
