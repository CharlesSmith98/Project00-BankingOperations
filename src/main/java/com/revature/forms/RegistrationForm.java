package com.revature.forms;

import java.util.Scanner;

public class RegistrationForm {

	private Scanner in;
	private String first;
	private String last;
	private String email;
	private String user;
	private String pass;
	
	public RegistrationForm(Scanner input) {
		this.in = input;
	}
	
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void promptData() {
		System.out.print("First Name: ");
		first = this.in.next();
		System.out.print("Last Name: ");
		last = this.in.next();
		System.out.print("Email: ");
		email = this.in.next();
		System.out.print("Username: ");
		user = this.in.next();
		System.out.print("Password: ");
		pass = this.in.next();
	}
	
}
