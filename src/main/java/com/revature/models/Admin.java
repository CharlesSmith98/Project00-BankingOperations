package com.revature.models;

public class Admin extends Employee {

	private static final long serialVersionUID = -7357843950095489701L;
	
	public Admin() {
		super("", "", "", "", 2);
	}
	
	public Admin(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password, 2);
	}
	
}
