package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.AccountApplication;

public interface AppDao {

List<AccountApplication> getAllApps();
	
	AccountApplication getApplicationByAppId(int appId);
	
	void createApp(AccountApplication a) throws SQLException;
	
	void updateApp(AccountApplication a);
	
	void deleteApp(AccountApplication a);
	
}
