package com.revature.models;

import java.io.Serializable;

/*
 * CREATE TABLE IF NOT EXISTS users(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	first_name varchar(30) NOT NULL,
	last_name varchar(30) NOT NULL,
	email varchar(50) NOT NULL,
	username varchar(30) UNIQUE NOT NULL,
	PASSWORD varchar(30) NOT NULL,
	ROLE int DEFAULT 0
);
 */

public class User implements Serializable{

	private static final long serialVersionUID = 4512243633013261409L;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private int role;
	
	// Default Constructor
	public User() {
		super();
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.role = 0;
	}
	
	// All-arg Constructor
	public User(int id, String firstName, String lastName, String email, 
			String username, String password, int role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	// Contructor to use when adding users to the database
	public User(String firstName, String lastName, String email, 
			String username, String password, int role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public void setRole(int r) {
		this.role = r;
	}
}
