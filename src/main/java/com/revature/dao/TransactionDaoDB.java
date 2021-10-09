package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.utils.ConnectionUtil;

public class TransactionDaoDB implements TransactionDao {

	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public List<Transaction> getAllTransactions() {
		
		List<Transaction> transactions = new ArrayList<>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from transactions";
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				int acctId = rs.getInt(2);
				double amount = rs.getDouble(3);
				String type = rs.getString(4);
				transactions.add(new Transaction(id, acctId, amount, type));
			}
			
			return transactions;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Transaction> getTransactionsFromAccount(int acctId) {
		
		List<Transaction> transactions = new ArrayList<>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from transactions t where t.acct_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, acctId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				double amount = rs.getDouble(3);
				String type = rs.getString(4);
				transactions.add(new Transaction(id, acctId, amount, type));
			}
			
			return transactions;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void createTransaction(Transaction t) {
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "insert into transactions (acct_id, amount, type)" 
					+ "values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, t.getAcctId());
			ps.setDouble(2, t.getAmount());
			ps.setString(3, t.getType());
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateTransaction(Transaction t) {

		try {
			Connection con = conUtil.getConnection();
			
			String sql = "update transactions set acct_id = ?, amount = ?, type = ?" 
					+ "where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, t.getAcctId());
			ps.setDouble(2, t.getAmount());
			ps.setString(3, t.getType());
			ps.setInt(4, t.getId());
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteTransaction(Transaction t) {
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "delete from transactions where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, t.getId());
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
