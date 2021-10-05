package com.revature.exceptions;

public class QuitApplicationException extends RuntimeException {

	public QuitApplicationException() {
		super("Application was safely terminated.");
	}
	
}
