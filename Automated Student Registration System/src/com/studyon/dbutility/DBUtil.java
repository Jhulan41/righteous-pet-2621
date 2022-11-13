package com.studyon.dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
	
	 private static String url;
	    private static String driverName;
	    private static String username;
	    private static String password;
	    
	    static {
	        
	        ResourceBundle rb= ResourceBundle.getBundle("dbdetails");
	        
	        url= rb.getString("db.url");
	        driverName= rb.getString("db.drivername");
	        username= rb.getString("db.username");
	        password= rb.getString("db.password");
	        
	        
	        
	    }
	
public  static Connection provideConnection() {
	
	 Connection conn = null;
     
     
     try {
         Class.forName(driverName);
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }
     
     
     
     try {
         conn = DriverManager.getConnection(url, username, password);
         
//         if(conn != null)
//				System.out.println("connected...");
//			else
//				System.out.println("not connected..");
     } catch (SQLException e) {
         e.printStackTrace();
     }
     
     
     
     
     return conn;
 }
		
		 

}
