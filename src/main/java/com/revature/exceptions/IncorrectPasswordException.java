package com.revature.exceptions;

public class IncorrectPasswordException extends RuntimeException {

	private static final long serialVersionUID = 4278546974494424222L;

	public IncorrectPasswordException() {
		super("Password is incorrect");
	}
	
}
