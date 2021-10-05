package com.revature.models;

public class AccountApplication {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private char accountType;
	private boolean pending;
	
	public AccountApplication() {
		this.id = -1;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.accountType = 'D';
		this.pending = true;
	}
	
	public AccountApplication(int id, String first, String last, String email, 
			char accountType, boolean pending) {
		this();
		setId(id);
		setFirstName(first);
		setLastName(last);
		setEmail(email);
		setAccountType(accountType);
		setPending(pending);
	}

	public int getId() {
		return id;
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

	public char getAccountType() {
		return accountType;
	}

	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}
	
}
