package com.shape.ShapeAppSpring.exception.domain;

@SuppressWarnings("serial")
public class EmailNotFoundException extends Exception{

	public EmailNotFoundException(String message) {
		super(message);
	}
}
