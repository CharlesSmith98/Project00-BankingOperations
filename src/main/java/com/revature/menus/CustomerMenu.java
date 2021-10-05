package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu extends Menu {

	private Scanner input;
	private boolean done;
	
	public CustomerMenu(Scanner in) {
		this.input = in;
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem applyForAccount = new MenuItem(1, "Apply for a new Account");
		MenuItem viewAccounts = new MenuItem(2, "View your Accounts");
		MenuItem signOut = new MenuItem(3, "Sign out");
		items.add(applyForAccount);
		items.add(viewAccounts);
		items.add(signOut);
		this.setMenuItems(items);
		this.setPrompt("-----Customer Options-----");
		this.done = false;
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
			break;
		case 2:
			break;
		case 3:
			setDone(true);
			break;
		}
	}

}
