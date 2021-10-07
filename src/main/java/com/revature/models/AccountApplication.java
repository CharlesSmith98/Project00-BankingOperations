package com.revature.models;

/*
 * CREATE TABLE IF NOT EXISTS accountApplications(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id int REFERENCES users(id) NOT NULL,
	accountType char(1) NOT NULL,
	pending bool DEFAULT true
);
 */

public class AccountApplication {

	private int id;
	private int userId;
	private char accountType;
	private boolean pending;
	
	public AccountApplication() {
		super();
	}
	
	public AccountApplication(int id, int userId, char accountType, boolean pending) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountType = accountType;
		this.pending = pending;
	}
	
	public AccountApplication(int userId, char accountType) {
		this.userId = userId;
		this.accountType = accountType;
		this.pending = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
