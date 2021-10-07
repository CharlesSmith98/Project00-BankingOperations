package com.revature.forms;

import java.util.Scanner;

public class ApplicationForm {

	private Scanner in;
	private char accountType;
	
	public ApplicationForm(Scanner input) {
		this.in = input;
	}

	public char getAccountType() {
		return accountType;
	}

	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}
	
	public void promptData() {
		System.out.println("\nWhat type of account do you wish to open?");
		System.out.println("Enter 1 for Checking");
		System.out.println("Enter 2 for Savings\n");
		System.out.print(">> ");
		int sel = this.in.nextInt();
		if (sel == 1) {
			this.accountType = 'C';
		} else if(sel == 2) {
			this.accountType = 'S';
		}
	}
	
}
