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
	private TryAgainMenu tryAgainMenu = new TryAgainMenu();
	
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
					// User fills out registration form
					registerSubMenu.promptData();
					String first = registerSubMenu.getFirst();
					String last = registerSubMenu.getLast();
					String email = registerSubMenu.getEmail();
					user = registerSubMenu.getUser();
					pass = registerSubMenu.getPass();
					
					// Register user into the database
					userService.register(first, last, email, user, pass);
					isUserNameValid = true;
				} catch (UsernameUnavailableException e) { 
					// If the username is already in use... 
					// (1) Log the exception
					Logging.logger.warn(e.getMessage());
					
					// (2) Tell the user to use a different one
					System.out.println("\nThat username is already taken.");
					System.out.println("Please use a different one.\n");
					tryAgainMenu.setInputValid(false);
					while(!tryAgainMenu.isInputValid()) {
						tryAgainMenu.display();
						int num = this.getInputScanner().nextInt();
						tryAgainMenu.processSelection(num);
						if(tryAgainMenu.getSelection() == 'Y') {
							tryAgainMenu.setInputValid(true);
						} else if(tryAgainMenu.getSelection() == 'N') {
							tryAgainMenu.setInputValid(true);
							isUserNameValid = true;
						}
					}
					
				}
			}
			break;
		case 2:
			// userObj is the user that is currently logged in
			User userObj = null;
			UserSignInForm signInSubMenu = 
					new UserSignInForm(this.getInputScanner());
			while (userObj == null) {
				try {
					// Prompt the user to sign In
					signInSubMenu.promptData();
					user = signInSubMenu.getUser();
					pass = signInSubMenu.getPass();
					
					// Log in
					userObj = userService.login(user, pass);
					continue;
				} catch (IncorrectPasswordException e) {
					// If the password is incorrect,
					// (1) Log the exception
					Logging.logger.warn(e.getMessage());
					System.out.println("\nThe password you entered is" 
							+ " incorrect\n");
					tryAgainMenu.setInputValid(false);
					while(!tryAgainMenu.isInputValid()) {
						tryAgainMenu.display();
						int num = this.getInputScanner().nextInt();
						tryAgainMenu.processSelection(num);
						if(tryAgainMenu.getSelection() == 'Y') {
							tryAgainMenu.setInputValid(true);
						} else if(tryAgainMenu.getSelection() == 'N') {
							tryAgainMenu.setInputValid(true);
							userObj = new User();
						}
					}
				}
			}
			if (userObj.getUsername().equals(""))
				break;
			
			// Log that the user has logged in
			Logging.logger.info("User: " + user + " logged in successfully");
			
			// If the user is an admin, give options for admins
			if (userObj.getRole() == 2) {
				AdminMenu adminMenu = new AdminMenu(getInputScanner(), userObj.getFirstName());
				while(!adminMenu.isDone()) {
					adminMenu.display();
					adminMenu.processSelection(getInputScanner().nextInt());
				}
			// If the user is an employee, give options for employees
			} else if (userObj.getRole() == 1) {
				EmployeeMenu employeeMenu = new EmployeeMenu(getInputScanner(), userObj.getFirstName());
				while(!employeeMenu.isDone()) {
					employeeMenu.display();
					employeeMenu.processSelection(getInputScanner().nextInt());
				}
			// If the user is a customer, give customer options
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
