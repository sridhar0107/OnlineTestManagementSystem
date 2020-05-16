package com.capgemini.TestQuestions.Service;

import java.math.BigInteger;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.TestQuestions.Dao.DaoInterface;
import com.capgemini.TestQuestions.Dao.DaoInterface1;
import com.capgemini.TestQuestions.entity.Question;
import com.capgemini.TestQuestions.entity.Test;


@Service
@Transactional
public class ServiceTest {

	
	
	@Autowired
	DaoInterface dao;
	
	@Autowired
	DaoInterface1 dao1;

	//Add question
	public Test addQuestion(BigInteger testId,Question question)
	{
	if(dao.existsById(testId))
	{
		Test t=dao.getOne(testId);
		question.setTest(t);
		t.getTestQuestions().add(question);
		t.setTestTotalMarks(t.getTestTotalMarks()+question.getQuestionMarks());
		
		return dao.save(t);
	}
	else
	{
      return null;
	}
	}
	
	
	
	
	//Update question
	public Question updateQuestion(BigInteger testId,Question question)
	{if(dao.existsById(testId))
	{
		Test t=dao.getOne(testId);
		question.setTest(t);
		return dao1.save(question);
	}
	else
	{
      return null;
	}
	}
	
	//Delete question
	public boolean  deleteQuestion(BigInteger testId,Question question)
	{
		if(dao.existsById(testId))
		{
			
			 dao1.delete(question);;
			return true;
		
		}
		else
		{
	      return false;
		} 
		
	}





	public Test calculateTotalMarks(Test test) {
	     Set<Question> s=test.getTestQuestions();
	     int testTotalMarks=0;
	     int testMarksScored=0;
	     Iterator<Question> it = s.iterator(); 
	     while (it.hasNext()) 
	     {
	          Question q= it.next(); 
	          
	          if(q.getChoosenAnswer()==q.getQuestionAnswer())
	          {
	        	  q.setMarksScored(q.getQuestionMarks());
	          }
	          testTotalMarks=testTotalMarks+q.getQuestionMarks();
	          testMarksScored=testMarksScored+q.getMarksScored();
	          q.setTest(test);
	          dao1.save(q);
		       
	     }
		test.setTestTotalMarks(testTotalMarks);
		test.setTestMarksScored(testMarksScored);
	     
		return test;
	}


}
