package com.shape.ShapeAppSpring.exception.domain;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{

	public UserNotFoundException(String message) {
		super(message);
	}

}
