package com.studyon.dbutility;

 

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DBUtil {
		
		public  static Connection getConnection() {
			
			Connection conn =null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("done");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String url = "jdbc:mysql://locolhost:3306/sb101_asrs";
			
			try {
				conn = DriverManager.getConnection(url, "root", "root");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return conn;
			
		}

	}


