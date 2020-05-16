package com.capgemini.TestQuestions.Dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.TestQuestions.entity.Question;

@Repository
public interface DaoInterface1 extends JpaRepository<Question,BigInteger> {

	
}
