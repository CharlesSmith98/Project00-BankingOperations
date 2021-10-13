package com.revature.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.AppDao;
import com.revature.dao.AppDaoDB;
import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.logging.Logging;
import com.revature.models.Account;
import com.revature.models.AccountApplication;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.EmployeeService;

public class EmployeeMenu extends Menu {

	private boolean done;
	private AppDao appDao = new AppDaoDB();
	private UserDao uDao = new UserDaoDB();
	private EmployeeService eServ = new EmployeeService(appDao);
	private String name;
	
	public EmployeeMenu(Scanner in, String n) {
		this.setInputScanner(in);
		this.name = n;
		this.done = false;
		this.setPrompt("-----Welcome, " + name + "-----");
		MenuItem viewAppsItem = new MenuItem(1, "View Open Applications for Accounts");
		MenuItem viewCustomers = new MenuItem(2, "View Customer Information");
		MenuItem signOut = new MenuItem(3, "Sign Off");
		ArrayList<MenuItem> items = new ArrayList<>();
		items.add(viewAppsItem);
		items.add(viewCustomers);
		items.add(signOut);
		this.setMenuItems(items);
	}
	
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public void processSelection(int sel) {
		Scanner in = this.getInputScanner();
		int num;
		switch(sel) {
		case 1: // APPROVE/ REJECT APPLICATION
			// Get list of open applications
			List<AccountApplication> apps = appDao.getPendingApps();
			System.out.println("-----Open Applications-----\n");
			for (AccountApplication a : apps) {
				System.out.println(a);
			}
			
			// Prompt user to select an application 
			System.out.print("\nPlease select an application.\n>> ");
			num = in.nextInt();
			boolean foundApp = false;
			for (AccountApplication a : apps) {
				if (num == a.getId())
					foundApp = true;
			}
			
			// If the application has been found, ask for approval/ disapproval
			if (foundApp) {
				System.out.println("You have selected Application: " 
						+ appDao.getApplicationByAppId(num) + "\n");
				System.out.println("Press 1 To Approve the application");
				System.out.println("Press 2 To Deny the application\n>> ");
				if(in.nextInt() == 1) {
					eServ.approve(num);
					Logging.logger.info("Application for Account Approved");
				} else if(in.nextInt() == 2) {
					eServ.deny(num);
					Logging.logger.info("Application for Account Rejected");
				}
			}
			break;
		case 2: // VIEW CUSTOMER INFO
			// Get list of customers
			List<User> customers = uDao.getCustomers();
			System.out.println("-----Customers-----\n");
			for (User u : customers) {
				System.out.println(u);
			}
			
			// Prompt user to select a customer
			System.out.println("\nPlease select a customer to view their accounts,");
			System.out.print("\n>> ");
			num = in.nextInt();
			
			// Get specified customer
			boolean foundCust = false;
			User user = null;
			for(User u : customers) {
				if(num == u.getId()) {
					foundCust = true;
					user = u;
				}
			}
			
			// If the customer exists in the database, list their transactions
			if(foundCust) {
				AccountDao accDao = new AccountDaoDB();
				TransactionDao tDao = new TransactionDaoDB();
				List<Account> accounts = accDao.getAccountsByUserId(user.getId());
				for (Account a : accounts) {
					System.out.println("\n" + a + "\n");
					for (Transaction t : tDao.getTransactionsFromAccount(a.getId())) {
						System.out.println(t);
					}
				}
			}
			
			break;
		case 3:
			this.done = true;
			break;
		}
	}

}
