package com.capgemini.UserLogin.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.UserLogin.Dao.DaoClass;
import com.capgemini.UserLogin.entity.Test;
import com.capgemini.UserLogin.entity.User;


@Service
@Transactional
public class ServiceMain {

	@Autowired
	private DaoClass dao;
	
	//User Login method
	public Optional<User> userLogin(String userId,String password)
	{
		
		return dao.userLogin(userId, password);
		
	}

    //Retrieving test details of particular User
	public Test userTest(String userId) {
		User u=dao.getOne(userId);
		return u.getUserTest();
	}
	
}
