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
import com.revature.forms.TransactionForm;
import com.revature.logging.Logging;
import com.revature.models.Account;
import com.revature.models.AccountApplication;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.EmployeeService;
import com.revature.services.TransactionService;

public class AdminMenu extends Menu {

	private Scanner input;
	private boolean done;
	private AppDao appDao = new AppDaoDB();
	private UserDao uDao = new UserDaoDB();
	private EmployeeService eServ = new EmployeeService(appDao);
	private TransactionService transactionService = new TransactionService();
	private String name;
	
	public AdminMenu(Scanner in, String n) {
		this.setInputScanner(in);
		this.input = in;
		this.name = n;
		this.done = false;
		this.setPrompt("-----Welcome, " + name + "-----");
		MenuItem viewAppsItem = new MenuItem(1, "View Open Applications for Accounts");
		MenuItem viewCustomers = new MenuItem(2, "View Customer Information");
		MenuItem cancelAccount = new MenuItem(3, "Cancel Account");
		MenuItem makeTransaction = new MenuItem(4, "Make Transaction");
		MenuItem signOut = new MenuItem(5, "Sign Off");
		ArrayList<MenuItem> items = new ArrayList<>();
		items.add(viewAppsItem);
		items.add(viewCustomers);
		items.add(cancelAccount);
		items.add(makeTransaction);
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
		List<User> customers = null;
		User user = null;
		Scanner in = this.getInputScanner();
		int num;
		switch(sel) {
		case 1: // APPLICATIONS APPROVAL/ DISAPPROVAL
			// Display Open Applications
			List<AccountApplication> apps = appDao.getPendingApps();
			System.out.println("-----Open Applications-----\n");
			for (AccountApplication a : apps) {
				System.out.println(a);
			}
			
			// Prompt the user to select an application
			System.out.print("\nPlease select an application.\n>> ");
			num = in.nextInt();
			boolean foundApp = false;
			for (AccountApplication a : apps) {
				if (num == a.getId())
					foundApp = true;
			}
			
			// If the application was found,
			// Prompt for approval/ disapproval
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
		case 2: // CUSTOMER INFORMATION
			// List all Customers
			customers = uDao.getCustomers();
			System.out.println("-----Customers-----\n");
			for (User u : customers) {
				System.out.println(u);
			}
			
			// Prompt the user to select a customer
			System.out.println("\nPlease select a customer to view their accounts,");
			System.out.print("\n>> ");
			num = in.nextInt();
			
			// Get the specified user
			user = null;
			for(User u : customers) {
				if(num == u.getId()) {
					user = u;
				}
			}
			
			// If the user exists in the database, list all of their transactions
			if(user != null) {
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
		case 3: // CANCEL ACCOUNTS
			// List all customers
			customers = uDao.getCustomers();
			System.out.println("-----Customers-----\n");
			for (User u : customers) {
				System.out.println(u);
			}
			
			// Prompt the user to select a customer
			System.out.println("\nPlease select a customer to view their accounts,");
			System.out.print("\n>> ");
			num = in.nextInt();
			
			// Get the specified customer
			user = null;
			for(User u : customers) {
				if(num == u.getId()) {
					user = u;
				}
			}
			
			// If the customer is in the database
			if(user != null) {
				AccountDao acctDao = new AccountDaoDB();
				
				// Get a list of their accounts
				List<Account> accounts = acctDao.getAccountsByUserId(user.getId());
				for (Account acct : accounts) {
					System.out.println(acct);
				}
				
				// Prompt the user to select an account to cancel
				System.out.println("\nWhich account would you like to cancel?");
				System.out.print("\n>> ");
				num = in.nextInt();
				
				// Confirm action with the user
				Account a = acctDao.getAccountById(num);
				System.out.println("You are trying to cancel the following account\n");
				System.out.println(a);
				System.out.println("\nAre you sure you want to cancel this account?");
				
				System.out.println("\nPress 1 for Yes");
				System.out.println("Press 2 for No");
				System.out.print("\n>> ");
				num = in.nextInt();
				
				if (num == 1) {
					acctDao.cancelAccount(a);
					Logging.logger.info("Account No " + a.getNum() + " has been closed");
				} else if (num == 2) {}
			}
			
			break;
		case 4: // MAKE TRANSACTIONS
			// List all accounts
			AccountDao accountDao = new AccountDaoDB();
			List<Account> accounts = accountDao.getAllAccounts();
			System.out.println("\n");
			
			for(Account a : accounts) {
				System.out.println(a);
			}
			
			// Prompt the user to select an account
			System.out.println("\n Select an account to make a transaction\n");
			
			System.out.print(">> ");
			int acctId = this.input.nextInt();
			
			// Get the specified account
			Account acct = null;
			for (Account a : accounts) {
				if(a.getId() == acctId)
					acct = a;
			}
			
			// If the account is in the database
			if(acct != null) {
				// Prompt the user for the type of transaction to make
				System.out.println("What kind of transaction would you like to make?\n");
				System.out.println("Press 1 for Deposit");
				System.out.println("Press 2 for Withdrawl");
				System.out.println("Press 3 for Transfer\n");
				System.out.print(">> ");
				TransactionForm tForm = new TransactionForm(acct.getId());
				tForm.checkType(input.nextInt());
				
				// Ask for input and check that it's valid
				boolean repeat = true;
				while(repeat) {
					System.out.println("How much?\n");
					System.out.print(">> $");
					tForm.setAmount(input.nextDouble());
					
					if(tForm.getAmount() > acct.getBalance() && (tForm.getType().equals("W") || tForm.getType().equals("TS"))) {
						System.out.println("\nYou do not have enough funds to perform this transaction\n");
						Logging.logger.warn("User does not have sufficient funds to perform this transaction");
					} else if(tForm.getAmount() < 0) {
						System.out.println("\nTransaction cannot have a negative amount\n");
						Logging.logger.warn("User tried to perform a transaction with a negative amount");
					} else {
						repeat = false;
					}
				}
				
				// Create transaction
				Transaction t = new Transaction(acct.getId(), tForm.getAmount(), tForm.getType());
				
				// Update the balance
				AccountService acctService = new AccountService(this.input);
				if (tForm.getType().equalsIgnoreCase("D")) {
					acctService.addFunds(t);
				} else if (tForm.getType().equalsIgnoreCase("W")) {
					acctService.deductFunds(t);
				}
				Logging.logger.info("Account Balance has been updated");
				
				// If it's a transfer, ask what account the account is going to
				long acctNum;
				if(tForm.getType().equals("TS")) {
					System.out.println("\nEnter the account number of the account you wish to transfer funds to.\n");
					System.out.print(">> ");
					acctNum = input.nextLong();
					Account transferRecipient = accountDao.getAccountByAccountNumber(acctNum);
					Transaction transferReceived = new Transaction(transferRecipient.getId(), tForm.getAmount(), "TR");
					acctService.deductFunds(t);
					acctService.addFunds(transferReceived);
					transactionService.makeTransaction(transferReceived);
					Logging.logger.info("Transfer Recipient Balance has been updated");
				}
				transactionService.makeTransaction(t);
				Logging.logger.info("Transaction recorded in the database");
			}
			
			break;
		case 5:
			this.done = true;
			break;
		}

	}

}
