import java.util.Scanner;

import com.revature.menus.UserMenu;
import com.revature.services.UserService;

public class BankingApp {

	private static String usersFile = "users.txt";
	private static UserService userService = new UserService(usersFile);
	
	public static void main(String[] args) {
		UserMenu userMenu = new UserMenu();
		userMenu.display();
		
		Scanner input = new Scanner(System.in);
		boolean done = false;
		while(!done) {
			int sel = Integer.parseInt(input.next());
			switch (sel) {
			case 1:
				//Create Account
				System.out.print("First Name: ");
				String first = input.next();
				System.out.print("Last Name: ");
				String last = input.next();
				System.out.print("Username: ");
				String user = input.next();
				System.out.print("Password: ");
				String pass = input.next();
				userService.register(first, last, user, pass);
				break;
			}
		}
	}	

}
