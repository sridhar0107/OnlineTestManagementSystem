package com.capgemini.UserJdbc.DaoImpl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.UserJdbc.entity.User;



@Repository
public interface Dao extends JpaRepository<User, String> {

	@Query("select userId,password,rePassword,emailId,phonenumber from User where userId>?1 and password<?2")
	Optional<User> userLogin(String userId,String password);
	
	@Modifying
	@Query("UPDATE User u SET u.password = :password,u.rePassword = :rePassword WHERE u.userId = :userId")
	int update(@Param("userId") String userId,@Param("password") String password,@Param("rePassword") String rePassword);
}
