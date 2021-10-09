package com.revature.models;

/*
 * CREATE TABLE IF NOT EXISTS transactions(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	acct_id int REFERENCES accounts(id) NOT NULL,
	amount NUMERIC NOT NULL,
	TYPE char(2) NOT NULL
);
 */

public class Transaction {

	private int id;
	private int acctId;
	private double amount;
	private String type;
	
	public Transaction() {
		this.id = 0;
		this.acctId = 0;
		this.amount = 0.0D;
		this.type = "";
	}
	
	public Transaction(int id, int acctId, double amount, String type) {
		this.id = id;
		this.acctId = acctId;
		this.amount = amount;
		this.type = type;
	}
	
	public Transaction(int acctId, double amount, String type) {
		this.acctId = acctId;
		this.amount = amount;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAcctId() {
		return acctId;
	}
	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		String str = null;
		if(type.equals("D")) {
			str = "Deposit";
		} else if(type.equals("W")) {
			str = "Withdrawl";
		} else if(type.equals("TR")) {
			str = "Recieved Transfer";
		} else {
			str = "Sent Transfer";
		}
			
		return str + " of " + "$" + amount;
	}
	
}
