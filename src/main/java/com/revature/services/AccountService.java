package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.models.Account;
import com.revature.models.Transaction;

public class AccountService {

	private AccountDao acctDao = new AccountDaoDB();
	private Scanner input;
	
	public AccountService(Scanner scanner) {
		this.input = scanner;
	}
	
	public void addFunds(Transaction t) {
		double amount = t.getAmount();
		Account a = acctDao.getAccountById(t.getAcctId());
		
		a.setBalance(a.getBalance() + amount);
		acctDao.updateAccount(a);
	}
	
	public void deductFunds(Transaction t) {
		double amount = t.getAmount();
		Account a = acctDao.getAccountById(t.getAcctId());
		
		a.setBalance(a.getBalance() - amount);
		acctDao.updateAccount(a);
	}
	
	public Account viewAccountsByUserId(int userId) {
		List<Account> accounts = acctDao.getAccountsByUserId(userId);
		System.out.println("-----Your Accounts-----\n");
		for(Account a : accounts) {
			System.out.println(a);
		}
		System.out.println("");
		
		System.out.print(">> ");
		int acctId = input.nextInt();
		
		Account acct = null;
		for (Account a : accounts) {
			if(a.getId() == acctId)
				acct = a;
		}
		
		return acct;
	}
	
}
