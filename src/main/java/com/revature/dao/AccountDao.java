package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {

	List<Account> getAllAccounts();
	List<Account> getAccountsByUserId(int userId);
	Account getAccountById(int id);
	Account getAccountByAccountNumber(long num);
	void createAccount(Account a);
	void updateAccount(Account a);
	void deleteAccount(Account a);
	void cancelAccount(Account a);
}
