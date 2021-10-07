package com.revature.forms;

import java.util.Scanner;

public class UserSignInForm {

	private Scanner in;
	private String user;
	private String pass;
	
	public UserSignInForm(Scanner input) {
		this.in = input;
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
		System.out.print("Username: ");
		user = this.in.next();
		System.out.print("Password: ");
		pass = this.in.next();
	}
	
}
