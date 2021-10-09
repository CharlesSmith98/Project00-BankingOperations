package com.revature.exceptions;

public class QuitApplicationException extends RuntimeException {

	private static final long serialVersionUID = -7753224846833453624L;

	public QuitApplicationException() {
		super("Application was safely terminated.");
	}
	
}
