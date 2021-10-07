package com.revature.services;

import java.sql.SQLException;

import com.revature.dao.AppDao;
import com.revature.logging.Logging;
import com.revature.models.AccountApplication;

public class CustomerService {	

	private AppDao appDao;
	
	public CustomerService(AppDao aDao) {
		this.appDao = aDao;
	}
	
	public void submitApplication(AccountApplication a) {
		try{
			appDao.createApp(a);
		} catch(SQLException e) {
			e.printStackTrace();
			Logging.logger.warn("Application was not submitted to the database.");
		}
		
	}
	
}
