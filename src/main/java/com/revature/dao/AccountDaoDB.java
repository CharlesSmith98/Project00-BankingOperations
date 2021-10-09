package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

public class AccountDaoDB implements AccountDao {

	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public List<Account> getAllAccounts() {

		List<Account> accountList = new ArrayList<>();
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM accounts";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1), rs.getInt(2), rs.getLong(3), rs.getLong(4), rs.getString(5).charAt(0), rs.getDouble(6)));
			}
			
			return accountList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Account> getAccountsByUserId(int userId) {
		
		List<Account> accounts = new ArrayList<>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from accounts where user_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				accounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getLong(3), rs.getLong(4), rs.getString(5).charAt(0), rs.getDouble(6)));
			}
			
			return accounts;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account getAccountById(int id) {

		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM accounts where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			int userId = rs.getInt(2);
			long acctNum = rs.getLong(3);
			long routing = rs.getLong(4);
			char type = rs.getString(5).charAt(0);
			double balance = rs.getDouble(6);
			
			return new Account(id, userId, acctNum, routing, type, balance);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void createAccount(Account a) {

		try {
			Connection con = conUtil.getConnection();
			
			String sql = "insert into accounts (user_id, accountType) values (?, ?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getUserId());
			ps.setString(2, a.getType()+"");
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateAccount(Account a) {

		try {
			Connection con = conUtil.getConnection();
			
			String sql = "update accounts set user_id = ?, acct_num = ?, route = ?, accountType = ?, balance = ? where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getUserId());
			ps.setLong(2, a.getNum());
			ps.setLong(3, a.getRoutingNum());
			ps.setString(4, a.getType()+"");
			ps.setDouble(5, a.getBalance());
			ps.setInt(6, a.getId());
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void deleteAccount(Account a) {
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "delete from accounts where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getId());
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
