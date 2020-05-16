package com.capgemini.UserLogin.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.UserLogin.entity.User;

@Repository
public interface DaoClass extends JpaRepository<User, String>{

	@Query("select userId,emailId,password,phonenumber,rePassword from User where userId=?1 and password=?2")
	Optional<User> userLogin(String userId,String password);
}
