package com.revature.menus;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.AppDao;
import com.revature.dao.AppDaoDB;
import com.revature.forms.ApplicationForm;
import com.revature.forms.TransactionForm;
import com.revature.logging.Logging;
import com.revature.models.Account;
import com.revature.models.AccountApplication;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;
import com.revature.services.TransactionService;

public class CustomerMenu extends Menu {

	private Scanner input;
	private User user;
	private boolean done;
	private AppDao aDao = new AppDaoDB();
	private AccountDao acctDao = new AccountDaoDB();
	private CustomerService customerService = new CustomerService(aDao);
	private AccountService acctService;
	private TransactionService transactionService = new TransactionService();
	
	public CustomerMenu(Scanner in, User u) {
		this.input = in;
		this.user = u;
		this.acctService = new AccountService(in);
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem applyForAccount = new MenuItem(1, "Apply for a new Account");
		MenuItem viewAccounts = new MenuItem(2, "View your Accounts");
		MenuItem makeTransaction = new MenuItem(3, "Make Transaction");
		MenuItem signOut = new MenuItem(4, "Sign out");
		items.add(applyForAccount);
		items.add(viewAccounts);
		items.add(makeTransaction);
		items.add(signOut);
		this.setMenuItems(items);
		this.setPrompt("-----Welcome, " + u.getFirstName() + "-----");
		this.done = false;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public boolean isDone() {
		return this.done;
	}
	
	@Override
	public void processSelection(int sel) {
		Account acct = null;
		switch(sel) {
		case 1: // APPLY FOR AN ACCOUNT
			ApplicationForm appForm = new ApplicationForm(this.input);
			appForm.promptData();
			AccountApplication app = new AccountApplication(user.getId(), appForm.getAccountType());
			customerService.submitApplication(app);
			break;
		case 2: // VIEW ACCOUNTS
			// Get list of accounts
			acct = acctService.viewAccountsByUserId(user.getId());
			
			// Display Account Info
			if(acct != null) {
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				System.out.println("\n-----Account Ending " + String.format("%04d",(acct.getNum() % 10000)) + "-----");
				System.out.println("\nBalance: " + formatter.format(acct.getBalance()) + "\n");
			}
			
			break;
		case 3: // MAKE TRANSACTION
			// Get account
			acct = acctService.viewAccountsByUserId(user.getId());
			
			// If the account exists in the database
			if(acct != null) {
				// Prompt for transaction type
				System.out.println("What kind of transaction would you like to make?\n");
				System.out.println("Press 1 for Deposit");
				System.out.println("Press 2 for Withdrawl");
				System.out.println("Press 3 for Transfer\n");
				System.out.print(">> ");
				TransactionForm tForm = new TransactionForm(acct.getId());
				tForm.checkType(input.nextInt());
				
				// Ask for the transaction amount and ensure that it's valid
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
				
				// Update balance
				if (tForm.getType().equalsIgnoreCase("D")) {
					acctService.addFunds(t);
				} else if (tForm.getType().equalsIgnoreCase("W")) {
					acctService.deductFunds(t);
				}
				Logging.logger.info("Account Balance has been updated");
				
				// If it's a transfer, ask what account it's going to
				long acctNum;
				if(tForm.getType().equals("TS")) {
					System.out.println("\nEnter the account number of the account you wish to transfer funds to.\n");
					System.out.print(">> ");
					acctNum = input.nextLong();
					Account transferRecipient = acctDao.getAccountByAccountNumber(acctNum);
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
		case 4:
			setDone(true);
			break;
		}
	}

}
