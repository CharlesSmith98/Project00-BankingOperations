package com.revature.models;

import java.text.NumberFormat;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;

/*
 * CREATE TABLE IF NOT EXISTS accounts(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id int REFERENCES users(id) NOT NULL,
	acct_num bigint DEFAULT nextval('accountNumberSeq'),
	route bigint DEFAULT nextval('routingSeq'),
	accountType char(1) NOT NULL,
	balance NUMERIC DEFAULT 0.00,
	unique(acct_num, route)
);
 */

public class Account {

	private int id;
	private int userId;
	private long num;
	private long routingNum;
	private char type;
	private double balance;
	
	public Account() {
		
	}
	
	public Account(int id, int userId, long num, long routingNum, char type, double balance) {
		super();
		this.id = id;
		this.userId = userId;
		this.num = num;
		this.routingNum = routingNum;
		this.type = type;
		this.balance = balance;
	}

	public Account(int userId, char type) {
		super();
		this.userId = userId;
		this.type = type;
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

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public long getRoutingNum() {
		return routingNum;
	}

	public void setRoutingNum(long routingNum) {
		this.routingNum = routingNum;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String str = "Checking";
		UserDao uDao = new UserDaoDB();
		User u = uDao.getUserById(userId);
		if(type == 'S')
			str = "Savings";
		return id + ". " + str + " Account Ending " + String.format("%04d",(num % 10000)) + " Owned By User: " + u.getUsername() 
			+ " ; Balance of " + formatter.format(balance);
	}
	
}
