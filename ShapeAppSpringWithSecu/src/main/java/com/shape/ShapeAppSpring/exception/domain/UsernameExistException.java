package com.shape.ShapeAppSpring.exception.domain;

@SuppressWarnings("serial")
public class UsernameExistException extends Exception{
	

	public UsernameExistException(String message) {
		super(message);
	}

}
