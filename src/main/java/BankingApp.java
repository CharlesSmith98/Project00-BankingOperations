import java.util.Scanner;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UsernameUnavailableException;
import com.revature.logging.Logging;
import com.revature.menus.UserMenu;
import com.revature.models.User;
import com.revature.services.UserService;

public class BankingApp {

	private static String usersFile = "users.txt";
	private static UserService userService = new UserService(usersFile);
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		boolean done = false;
		while(!done) {
			UserMenu userMenu = new UserMenu();
			userMenu.display();
			User userObj = null;
			String user = null;
			String pass = null;
			int sel = Integer.parseInt(input.next());
			switch (sel) {
			case 1:
				boolean isUserNameValid = false;
				while (!isUserNameValid) {
					try {
						// Create Account
						System.out.print("First Name: ");
						String first = input.next();
						System.out.print("Last Name: ");
						String last = input.next();
						System.out.print("Username: ");
						user = input.next();
						System.out.print("Password: ");
						pass = input.next();
						userService.register(first, last, user, pass);
						isUserNameValid = true;
					} catch (UsernameUnavailableException e) {
						Logging.logger.warn(e.getMessage());
						System.out.println("\nThat username is already taken.");
						System.out.println("Please use a different one.\n");
					}
				}
				break;
			case 2:
				while (userObj == null) {
					try {
						// Sign In
						System.out.print("Username: ");
						user = input.next();
						System.out.print("Password: ");
						pass = input.next();
						userObj = userService.login(user, pass);
					} catch (IncorrectPasswordException e) {
						Logging.logger.warn(e.getMessage());
						System.out.println("\nThe password you entered is" 
								+ " incorrect\n");
					}
				}
				break;
			}
		}// End While Loop
		input.close();
	}	

}
