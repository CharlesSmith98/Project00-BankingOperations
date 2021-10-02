import java.util.ArrayList;

import com.revature.models.Admin;
import com.revature.models.Employee;
import com.revature.models.User;

public class BankingApp {

	public static void main(String[] args) {
		ArrayList<User> list = new ArrayList<>();
		list.add(new User());
		list.add(new Employee());
		list.add(new Admin());
		
		for (User u : list) {
			System.out.println(u.getRole());
		}
	}	

}
