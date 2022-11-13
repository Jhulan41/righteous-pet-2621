package com.studyon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studyon.dbutility.DBUtil;
import com.studyon.exceptions.CourseException;
import com.studyon.exceptions.StudentException;
import com.studyon.model.Course;
import com.studyon.model.CourseDetails;
import com.studyon.model.Student;

public class StudentDaoImpl implements StudentDao{

	@Override
	public Student checkStudentAuthenticity(String username, String password) throws StudentException {
		Student s = null;
		 
	 	String id = "";
		String pass = "";
		
		try(Connection conn=DBUtil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement("select * from student where email = ? AND password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				 id = rs.getString("email");
				 pass = rs.getString("password");
				 
				  
				
			}
			
			if(username.equals(id) && password.equals(pass)) {
				
				int roll = rs.getInt("roll");
				String name = rs.getString("sname");
				String add = rs.getString("address");
				String email = rs.getString("email");
				String p = rs.getString("password")	;			
				s = new Student(roll, name, add, email, p);
			}
			else {
				throw new StudentException("Wrong username or password");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			throw new StudentException(e.getMessage());
		}
		
	 
	return s;
}

	@Override
	public String studentSignUp(String name, String address, String email, String password) throws StudentException {
		String message = "SignUp Failed";
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps =conn.prepareStatement("insert into student(sname,address,email,password) values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, email);
			ps.setString(4, password);
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				message = "SignUp Successful";
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new StudentException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String updateStudentName(Student s, String newName) throws StudentException {
		String message = "Can't be updated";
		int roll = s.getRoll();
		try(Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Update student set sname=? where roll = ?");
			ps.setString(1, newName);
			ps.setInt(2, roll);
			int x = ps.executeUpdate();
			if(x>0) {
				message = "Name updated from "+s.getName()+" to "+newName;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String updateStudentAddress(Student s, String newAddress) throws StudentException {
		String message = "Can't be updated";
		int roll = s.getRoll();
		try(Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Update student set address=? where roll = ?");
			ps.setString(1, newAddress);
			ps.setInt(2, roll);
			int x = ps.executeUpdate();
			if(x>0) {
				message = "Address Changed from "+s.getAddress()+" to "+newAddress;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String updateStudentEmail(Student s, String newEmail) throws StudentException {
		String message = "Can't be updated";
		int roll = s.getRoll();
		try(Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Update student set email=? where roll = ?");
			ps.setString(1, newEmail);
			ps.setInt(2, roll);
			int x = ps.executeUpdate();
			if(x>0) {
				message = "Email changed from "+s.getEmail()+" to "+newEmail;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String updateStudentPassword(Student s, String newPass) throws StudentException {
		String message = "Can't be updated";
		int roll = s.getRoll();
		try(Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Update student set password=? where roll = ?");
			ps.setString(1, newPass);
			ps.setInt(2, roll);
			int x = ps.executeUpdate();
			if(x>0) {
				message = "Password changed from "+s.getPassword()+" to "+newPass;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String studentRegistration(Student s, int courseID, int batchID) throws StudentException {
		String message = "Registration Failed";
		int roll = s.getRoll();
		try(Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into studentRegistration values(?,?,?)");
			ps.setInt(1, courseID);
			ps.setInt(2, roll);
			ps.setInt(3, batchID);
			int x = ps.executeUpdate();
			if(x>0) {
				message = "Student successfuly registerd";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<CourseDetails> courseDetails() {

		List<CourseDetails> courses = new ArrayList<>();

		try(Connection conn =  DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batches");
			
			ResultSet rs1 = ps.executeQuery();
			while(rs1.next()) {
				int z = rs1.getInt("cid");
				PreparedStatement ps2 = conn.prepareStatement("select * from course where courseID=?");
				ps2.setInt(1, z);
				ResultSet rs2=ps2.executeQuery();
				
				  int batchid = rs1.getInt("batchID");
				  int courseid = z;
				  String courseName = rs2.getString("courseName");
				  String session= rs2.getString("session");
				  int courseFee= rs2.getInt("courseFee");
				  int  numberOfSeatsAvailable=rs1.getInt("numberOfSeatsAvailable");
				  
				  
				  
				  CourseDetails cd = new CourseDetails(batchid, courseid, courseName, session, courseFee, numberOfSeatsAvailable);
				  
				  courses.add(cd);
				  
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			 System.out.println(e.getMessage());
		}
		
//		if(courses.size()==0) {
//			 System.out.println("No Course Available");
//		}
		
		
		return courses;
	}
	
	
	

	}


