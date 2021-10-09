package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	List<User> getAllUsers();
	List<User> getCustomers();
	
	User getUserByUsername(String username);
	User getUserById(int id);
	void createUser(User u) throws SQLException;
	void updateUser(User u);
	void deleteUser(User u);
	
}
