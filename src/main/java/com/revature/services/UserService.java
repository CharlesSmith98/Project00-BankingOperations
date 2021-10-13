package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.UserDao;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UsernameUnavailableException;
import com.revature.logging.Logging;
import com.revature.models.User;

public class UserService {

	//private String fileName;
	private UserDao uDao;
	
	public UserService(UserDao u) {
		//this.fileName = fileName;
		this.uDao = u;
	}
	
	public void register(String first, String last, String email, String user, String pass) {
		
		List<User> userList = null;
		try {
			userList = uDao.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (User u : userList) {
			if (user.equals(u.getUsername())) {
				throw new UsernameUnavailableException();
			}
		}
		
		User u = new User(first, last, email, user, pass, 0);
		
		try {
			uDao.createUser(u);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("User: " + user + " was not registered!!!");
			System.out.println("Please try again later.");
			return;
		}
		
		Logging.logger.info("User: " + user + " was registered in the database.");
		System.out.println("User: " + user + " has been successsfully " 
				+ "registered!!");
		
	}
	
	public User login(String user, String pass) {
		User u = null;
		try {
			u = uDao.getUserByUsername(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(u != null) {
			if(pass.equals(u.getPassword())) {
				return u;
			}
			throw new IncorrectPasswordException();
		}
		
		
		System.out.println("\nUser: " + user + " does not exist!\n");
		return null;
	}
	
}
