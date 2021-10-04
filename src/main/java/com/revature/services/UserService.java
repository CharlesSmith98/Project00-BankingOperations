package com.revature.services;

import java.util.ArrayList;

import com.revature.dao.FileIO;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UsernameUnavailableException;
import com.revature.models.User;

public class UserService {

	private String fileName;
	private FileIO<User> userFileIO;
	
	public UserService(String fileName) {
		this.fileName = fileName;
		this.userFileIO = new FileIO<>(fileName);
	}
	
	public void register(String first, String last, String user, String pass) {
		
		ArrayList<User> userList = null;
		try {
			userList = userFileIO.readObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (User u : userList) {
			if (user.equals(u.getUsername())) {
				throw new UsernameUnavailableException();
			}
		}
		
		if(userList.add(new User(first, last, user, pass, 0))) {
			System.out.println("User: " + user + " has been successsfully " 
					+ "registered!!");
		}
		userFileIO.writeObjects(userList);
	}
	
	public User login(String user, String pass) {
		ArrayList<User> userList = null;
		try {
			userList = userFileIO.readObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (User u : userList) {
			if (user.equals(u.getUsername())) {
				if (pass.equals(u.getPassword())) {
					return u;
				}
				throw new IncorrectPasswordException();
			}
		}
		
		System.out.println("\nUser: " + user + " does not exist!\n");
		return null;
	}
	
}
