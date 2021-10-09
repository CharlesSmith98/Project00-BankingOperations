package com.revature.dao;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDao {

	List<Transaction> getAllTransactions();
	List<Transaction> getTransactionsFromAccount(int acctId);
	void createTransaction(Transaction t);
	void updateTransaction(Transaction t);
	void deleteTransaction(Transaction t);
	
}
