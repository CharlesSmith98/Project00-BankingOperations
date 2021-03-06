import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.QuitApplicationException;
import com.revature.logging.Logging;
import com.revature.menus.UserMenu;

public class BankingApp {

	/*private static UserDao uDao = new UserDaoDB();
	private static UserService userService = new UserService(uDao);*/
	
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
