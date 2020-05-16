package com.capgemini.UserJdbc.Service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.UserJdbc.DaoImpl.Dao;
import com.capgemini.UserJdbc.entity.User;





	@Service
	@Transactional
	public class ServiceMain {

		@Autowired
		private Dao dao;
		
        //Adding User details to database
		public User userRegistration(User user)
		{
			if(!dao.existsById(user.getUserId()))
			{
			return dao.save(user);
			
			}
			else
			{
				return null;
			}
		}
		
		//User Login
		public User userLogin(String userId,String password)
		{
			
			if(dao.existsById(userId))
			{
				
				
				return dao.getOne(userId);
			}
			else
			{
			return null;
			}
			
		}
		
		//Updating password in database
		public int passwordRecovery(String userId,String password,String rePassword)
		{
			if(dao.existsById(userId))
			{
			return dao.update(userId, password, rePassword);	
			}
			else
			{
				return 0;
			}
			
			
		}
		
	}


