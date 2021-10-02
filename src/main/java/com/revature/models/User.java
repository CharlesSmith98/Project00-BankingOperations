package com.revature.models;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 4512243633013261409L;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int role;
	
	public User() {
		super();
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.role = 0;
	}
	
	public User(String firstName, String lastName, String username,
			String password, int role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}
	
}
