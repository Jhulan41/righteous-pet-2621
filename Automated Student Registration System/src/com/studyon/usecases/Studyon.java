package com.studyon.usecases;

import java.util.Scanner;

import com.studyon.dao.AdminDao;
import com.studyon.dao.AdminDaoimpl;
import com.studyon.exceptions.AdminException;

public class Studyon {

	public static void main(String[] args) {
 
		 boolean choice = true;
		 
		 while(choice != false){
			 System.out.println("Welcome to STUDYON");
			 System.out.println("Please Enter a Choice : ");
			 
			 System.out.println("\n 1: Login as Admin \n 2: Login as Student \n 3: New student Registration \n 4: Exit");
			 int option = 0; 
			 Scanner sc = new Scanner(System.in);
			 option = sc.nextInt();
			 switch(option) {
			 
			 case 1:
				  try {
					adminLogin();
				} catch (AdminException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 
			 case 2:
				 studentLogin();
				 break;
				 
				 
			 case 3:
				 studentRegistration();
				 break;
				 
				 
			 case 4: choice = false;
			 
			 }
			 
		 }

	}

	private static void studentRegistration() {
		// TODO Auto-generated method stub
		
	}

	private static boolean studentLogin() {
		return false;
		// TODO Auto-generated method stub
		
	}

	private static boolean adminLogin() throws AdminException {
		boolean res = false;
		
//		String id1="";
//		String pass = "";
		
		String userName ="";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter UserName :");
		userName = sc.next();
		System.out.println("Enter Password :");
		password = sc.next();
		
		AdminDao dao = new AdminDaoimpl();
		
		if(dao.checkAdminAuthenticity(userName, password)) {
			
			System.out.println("login successful");
		}
		else {
			System.out.println("Invalid UserName or Password");
		}
 
		
		return res; 	 
		
	}

	 

}
