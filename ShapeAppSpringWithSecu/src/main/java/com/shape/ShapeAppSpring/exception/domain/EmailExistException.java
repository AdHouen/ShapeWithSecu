package com.shape.ShapeAppSpring.exception.domain;

@SuppressWarnings("serial")
public class EmailExistException extends Exception{

	public EmailExistException(String message) 
	{
		super(message);
	}

}
