package com.revature.forms;

public class TransactionForm {

	private String type;
	private double amount;
	private int acctId;

	public TransactionForm(int acctId) {
		this.acctId = acctId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAcctId() {
		return acctId;
	}

	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}
	
	public void checkType(int num) {
		switch(num) {
		case 1:
			this.type = "D";
			break;
		case 2:
			this.type = "W";
			break;
		case 3:
			this.type = "TS";
			break;
		default:
			break;
		}
	}
	
}
