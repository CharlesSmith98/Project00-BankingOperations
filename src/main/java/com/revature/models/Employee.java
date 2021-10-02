package com.revature.models;

public class Employee extends User{

	private static final long serialVersionUID = -4735713189822199184L;
	
	public Employee() {
		super("", "", "", "", 1);
	}
	
	Employee(String firstame, String lastName, String username, String password, int role) {
		super(lastName, lastName, username, password, role);
	}
	
}
