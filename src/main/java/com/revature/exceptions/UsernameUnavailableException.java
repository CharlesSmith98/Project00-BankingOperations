package com.revature.exceptions;

public class UsernameUnavailableException extends RuntimeException{

	private static final long serialVersionUID = 7730690672182475871L;

	public UsernameUnavailableException() {
		super("User tried registering a username that already exists.");
	}
	
}
