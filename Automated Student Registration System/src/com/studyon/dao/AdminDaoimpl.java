package com.studyon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.studyon.dbutility.DBUtil;
import com.studyon.exceptions.AdminException;

public class AdminDaoimpl implements AdminDao{

	@Override
	public boolean checkAdminAuthenticity(String username, String password) throws AdminException {
		boolean access = false; 
		
		String id ="";
		String pass = "";
		
		try(Connection conn=DBUtil.getConnection()){
			
			PreparedStatement ps=conn.prepareStatement("select * from admin ");
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getString(1);
				pass = rs.getString(2);
				
			}
			
			if(username.equals(id) && password.equals(pass)) {
				access = true;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AdminException("Wrong username or password");
		}
		
		
		
		
		return access;
	}

}
