package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.AppDao;
import com.revature.dao.AppDaoDB;
import com.revature.forms.ApplicationForm;
import com.revature.models.AccountApplication;
import com.revature.models.User;
import com.revature.services.CustomerService;

public class CustomerMenu extends Menu {

	private Scanner input;
	private User user;
	private boolean done;
	private AppDao aDao = new AppDaoDB();
	private CustomerService customerService = new CustomerService(aDao);
	
	public CustomerMenu(Scanner in, User u) {
		this.input = in;
		this.user = u;
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem applyForAccount = new MenuItem(1, "Apply for a new Account");
		MenuItem viewAccounts = new MenuItem(2, "View your Accounts");
		MenuItem signOut = new MenuItem(3, "Sign out");
		items.add(applyForAccount);
		items.add(viewAccounts);
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
		switch(sel) {
		case 1:
			ApplicationForm appForm = new ApplicationForm(this.input);
			appForm.promptData();
			AccountApplication app = new AccountApplication(user.getId(), appForm.getAccountType());
			customerService.submitApplication(app);
			break;
		case 2:
			break;
		case 3:
			setDone(true);
			break;
		}
	}

}
