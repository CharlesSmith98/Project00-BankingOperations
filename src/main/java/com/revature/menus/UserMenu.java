package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.QuitApplicationException;
import com.revature.exceptions.UsernameUnavailableException;
import com.revature.forms.RegistrationForm;
import com.revature.forms.UserSignInForm;
import com.revature.logging.Logging;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserMenu extends Menu {
	
	private UserDao uDao = new UserDaoDB();
	private UserService userService = new UserService(uDao);
	
	public UserMenu(Scanner input) {
		super();
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem register = new MenuItem(1, "Create New Account");
		MenuItem signIn = new MenuItem(2, "Sign In to an Existing Account");
		MenuItem quit = new MenuItem(3, "Quit Application");
		items.add(register);
		items.add(signIn);
		items.add(quit);
		this.setMenuItems(items);
		this.setInputScanner(input);
		this.setPrompt("-----Main Menu-----");
		this.display();
		processSelection(input.nextInt());
	}
	
	@Override
	public void processSelection(int sel) {
		String user = null;
		String pass = null;
		switch (sel) {
		case 1:
			boolean isUserNameValid = false;
			RegistrationForm registerSubMenu = 
					new RegistrationForm(this.getInputScanner());
			while (!isUserNameValid) {
				try {
					// Create Account
					registerSubMenu.promptData();
					String first = registerSubMenu.getFirst();
					String last = registerSubMenu.getLast();
					String email = registerSubMenu.getEmail();
					user = registerSubMenu.getUser();
					pass = registerSubMenu.getPass();
					userService.register(first, last, email, user, pass);
					isUserNameValid = true;
				} catch (UsernameUnavailableException e) {
					Logging.logger.warn(e.getMessage());
					System.out.println("\nThat username is already taken.");
					System.out.println("Please use a different one.\n");
				}
			}
			break;
		case 2:
			User userObj = null;
			UserSignInForm signInSubMenu = 
					new UserSignInForm(this.getInputScanner());
			while (userObj == null) {
				try {
					// Sign In
					signInSubMenu.promptData();
					user = signInSubMenu.getUser();
					pass = signInSubMenu.getPass();
					userObj = userService.login(user, pass);
				} catch (IncorrectPasswordException e) {
					Logging.logger.warn(e.getMessage());
					System.out.println("\nThe password you entered is" 
							+ " incorrect\n");
				}
			}
			Logging.logger.info("User: " + user + " logged in successfully");
			if (userObj.getRole() == 2) {
				
			} else if (userObj.getRole() == 1) {
				
			} else if (userObj.getRole() == 0) {
				CustomerMenu custMenu = new CustomerMenu(getInputScanner(), userObj);
				while(!custMenu.isDone()) {
					custMenu.display();
					custMenu.processSelection(getInputScanner().nextInt());
				}
			}
			break;
		case 3:
			throw new QuitApplicationException();
		}
	}

}
