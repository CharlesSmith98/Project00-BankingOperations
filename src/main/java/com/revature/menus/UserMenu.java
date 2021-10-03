package com.revature.menus;

import java.util.ArrayList;

import com.revature.services.UserService;

public class UserMenu extends Menu {
	
	private UserService userService = new UserService("users.txt");
	
	public UserMenu() {
		super();
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem register = new MenuItem(1, "Create New Account");
		MenuItem signIn = new MenuItem(2, "Sign In to an Existing Account");
		MenuItem quit = new MenuItem(3, "Quit Application");
		items.add(register);
		items.add(signIn);
		items.add(quit);
		this.setMenuItems(items);
		this.setPrompt("-----Main Menu-----");
	}

}
