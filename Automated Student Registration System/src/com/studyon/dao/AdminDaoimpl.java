package com.studyon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.studyon.dbutility.DBUtil;
import com.studyon.exceptions.AdminException;

public class AdminDaoImpl implements AdminDao{

	@Override
	public boolean checkAdminAuthenticity(String username, String password) throws AdminException {
		 boolean result = false;
		 
		 	String id = "";
			String pass = "";
			
			try(Connection conn=DBUtil.provideConnection()){
				
				PreparedStatement ps=conn.prepareStatement("select * from admin where username = ? AND password = ?");
				
				ps.setString(1, username);
				ps.setString(2, password);
				
				ResultSet rs= ps.executeQuery();
				
				if(rs.next()) {
					 id = rs.getString("username");
					 pass = rs.getString("password");
					 
					  
					
				}
				
				if(username.equals(id) && password.equals(pass)) {
					result = true;
				}
				else {
					throw new AdminException("Wrong username or password");
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new AdminException(e.getMessage());
			}
			
		 
		return result;
	}

}
