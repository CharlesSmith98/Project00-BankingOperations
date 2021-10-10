package com.revature.services;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.models.Account;
import com.revature.models.Transaction;

public class AccountService {

	private AccountDao acctDao = new AccountDaoDB();
	
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
	
}
