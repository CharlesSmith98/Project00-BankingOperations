package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountApplication;
import com.revature.utils.ConnectionUtil;

public class AppDaoDB implements AppDao {

	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public List<AccountApplication> getAllApps() {

		List<AccountApplication> appList = new ArrayList<AccountApplication>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM accountapplications";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				appList.add(new AccountApplication(rs.getInt(1), rs.getInt(2), rs.getString(3).charAt(0), rs.getBoolean(4)));
			}
			
			return appList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AccountApplication> getPendingApps() {
		
		List<AccountApplication> appList = new ArrayList<AccountApplication>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM accountapplications where pending = true";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				appList.add(new AccountApplication(rs.getInt(1), rs.getInt(2), rs.getString(3).charAt(0), rs.getBoolean(4)));
			}
			
			return appList;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountApplication getApplicationByAppId(int appId) {
		AccountApplication app = null;
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM accountapplications apps WHERE apps.id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, appId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt(2);
				char accountType = rs.getString(3).charAt(0);
				boolean pending = rs.getBoolean(4);
				app = new AccountApplication(appId, userId, accountType, pending);
			}
			return app;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createApp(AccountApplication a) throws SQLException {
	
		Connection con = conUtil.getConnection();
		
		String sql = "INSERT INTO accountapplications (user_id, accounttype) " 
				+ "VALUES (?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, a.getUserId());
		ps.setString(2, a.getAccountType() + "");
		
		ps.execute();
		
	}

	@Override
	public void updateApp(AccountApplication a) {

		try {
			Connection con = conUtil.getConnection();
			
			String sql = "UPDATE accountapplications SET user_id = ?," 
					+ " accountType = ?, pending = ? WHERE id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, a.getUserId());
			ps.setString(2, a.getAccountType() + "");
			ps.setBoolean(3, a.isPending());
			ps.setInt(4, a.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteApp(AccountApplication a) {
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "delete from accountapplications where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, a.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
