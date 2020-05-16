package com.capgemini.Exceptions;

public class UserExceptions extends RuntimeException{

	public UserExceptions(String msg)
	{
		super(msg);
	}
	public UserExceptions(String msg,Throwable e)
	{
		super(msg,e);
	}
}
