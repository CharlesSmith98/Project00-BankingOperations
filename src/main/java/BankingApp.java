import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.QuitApplicationException;
import com.revature.exceptions.UsernameUnavailableException;
import com.revature.logging.Logging;
import com.revature.menus.UserMenu;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.services.UserService;

public class BankingApp {

	private static String usersFile = "users.txt";
	private static UserService userService = new UserService(usersFile);
	
	public static void main(String[] args) {
		
		Logging.logger.info("Application Started");
		boolean done = false;
		Scanner input = new Scanner(System.in);
		while(!done) {
			try{
				UserMenu userMenu = new UserMenu(input);
			} catch (NoSuchElementException e){
				Logging.logger.fatal("NoSuchElementException caused application to terminate.");
				done = true;
			} catch (QuitApplicationException e) {
				Logging.logger.info(e.getMessage());
				done = true;
			}
		}
		input.close();
	}	

}
