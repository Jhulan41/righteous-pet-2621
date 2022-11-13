package com.studyon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studyon.dbutility.DBUtil;
import com.studyon.exceptions.BatchException;
import com.studyon.exceptions.CourseException;
import com.studyon.exceptions.StudentException;
import com.studyon.model.Batch;
import com.studyon.model.Course;
import com.studyon.model.Student;

public class BatchDaoImpl implements BatchDao{

	@Override
	public String createBatch(int cid, int noOfSeats) throws BatchException {
		String message  = "Batch can't be created";
		
		try(Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("update  course set numberOfBatches = numberOfBatches + 1 where courseID = ?;");
			PreparedStatement ps2 = conn.prepareStatement("insert into batches(cid,numberOfSeatsAvailable) values(?,?)");
			
			ps1.setInt(1, cid);
			ps2.setInt(1, cid);
			ps2.setInt(2, noOfSeats);
			
			ps1.executeUpdate();
			int x = ps2.executeUpdate();
			
			if(x>0) {
				message = "Batch Created Successfullly";
			}
			else {
				throw new BatchException("courseID NOT Found");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BatchException(e.getMessage());
		}
		return message;
	}

	@Override
	public List<Batch> getAllbatchdetails() throws BatchException {

		List<Batch> batches  = new ArrayList<>();
		
		try(Connection conn =  DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from batches");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				  int cid = rs.getInt("cid");
				  int batchID= rs.getInt("batchID"); 
				  int numberOfSeatsAvailable=rs.getInt("numberOfSeatsAvailable");
				  
				  
				  
				  Batch bs = new Batch(batchID, cid, numberOfSeatsAvailable);
				  
				  batches.add(bs);
				  
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new BatchException(e.getMessage());
		}
		
		if(batches.size()==0) {
			throw new BatchException("There is no batches available at this moment");
		}
		
		return batches;
	}

	@Override
	public List<Student> StudentFromAbatch(int batchID) throws StudentException {

		List<Student> student  = new ArrayList<>();
		
		try(Connection conn =  DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from student where roll=(select roll from student_course_allocation where batchid =?)");
			
			ps.setInt(1, batchID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int roll = rs.getInt("roll");
				String name = rs.getString("sname");
				String add = rs.getString("address");
				String email = rs.getString("email");
				String p = rs.getString("password")	;	
				
				Student s = new Student(roll, name, add, email, p);
				  
				  student.add(s);
				  
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new StudentException(e.getMessage());
		}
		
		if(student.size()==0) {
			throw new  StudentException("There is no student available in this batch");
		}
		
		return student;

	}

}
