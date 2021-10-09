package com.revature.services;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.AppDao;
import com.revature.models.Account;
import com.revature.models.AccountApplication;

public class EmployeeService {

	private AppDao appDao;
	private AccountDao acctDao = new AccountDaoDB(); 
	public EmployeeService(AppDao appDao) {
		this.appDao = appDao;
	}
	
	public void approve(int appId) {
		AccountApplication app = appDao.getApplicationByAppId(appId);
		app.setPending(false);
		appDao.updateApp(app);
		
		// Create Account
		acctDao.createAccount(new Account(app.getUserId(), app.getAccountType()));
	}
	
	public void deny(int appId) {
		AccountApplication app = appDao.getApplicationByAppId(appId);
		app.setPending(false);
		appDao.updateApp(app);
	}
	
}
