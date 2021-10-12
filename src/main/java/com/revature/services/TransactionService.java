package com.revature.services;

import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoDB;
import com.revature.models.Transaction;

public class TransactionService {

	private TransactionDao transactionDao = new TransactionDaoDB();
	
	public void makeTransaction(Transaction t) {
		transactionDao.createTransaction(t);
	}
	
}
