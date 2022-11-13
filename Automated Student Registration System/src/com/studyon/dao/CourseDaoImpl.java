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
import com.studyon.model.Course;

public class CourseDaoImpl implements CourseDao{

	@Override
	public List<Course> getAllcoursedetails() throws CourseException {
		// TODO Auto-generated method stub
		List<Course> courses  = new ArrayList<>();
		
		try(Connection conn =  DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				  int cid = rs.getInt("courseID");
				  String courseName = rs.getString("courseName");
				  String session= rs.getString("session");
				  int numberOfBatches=rs.getInt("numberOfBatches");
				  int courseFee= rs.getInt("courseFee");
				  String courseDuration= rs.getString("courseDuration");
				  
				  Course cs = new Course(cid, courseName, session, numberOfBatches, courseFee, courseDuration);
				  
				  courses.add(cs);
				  
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CourseException(e.getMessage());
		}
		
		if(courses.size()==0) {
			throw new CourseException("There is no course available at this moment");
		}
		
		return courses;
	}

	@Override
	public String updateFeeOfACourse(int cid, int newAmount) throws CourseException {
		 String message = "Update Failed";
		 
		 try(Connection conn =  DBUtil.provideConnection()) {
			 
			 PreparedStatement ps = conn.prepareStatement(" UPDATE course SET courseFee = ? WHERE courseID = ?");
			 
			 ps.setInt(1, newAmount);
			 ps.setInt(2, cid);
			 
			int x =  ps.executeUpdate();
			
			if(x>0) {
				message = "Successfully Updated";
			}
			else {
				throw new CourseException("courseID NOT Found");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CourseException(e.getMessage());
		}
		 
		 
		 return message;
	}

	@Override
	public String deleteACourse(int cid) throws CourseException {
		String message = "Can't be deleted";
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("DELETE FROM batches WHERE cid = ?");
			PreparedStatement ps2 = conn.prepareStatement("DELETE FROM student_course_allocation WHERE cid = ?");
			PreparedStatement ps3 = conn.prepareStatement("DELETE FROM Course WHERE courseID = ?");
			ps1.setInt(1, cid);
			ps2.setInt(1, cid);
			ps3.setInt(1, cid);
			ps2.executeUpdate();
			ps1.executeUpdate();
			
			int z = ps3.executeUpdate();
			if(z>0) {
				message = "Course deleted Successfully";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		return message;
	}

	@Override
	public Course searchACourse(int cid) throws CourseException {
		Course cs = new Course();
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("select * from course where courseID =?");
			ps.setInt(1, cid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int courseid = rs.getInt("courseID");
				  String courseName = rs.getString("courseName");
				  String session= rs.getString("session");
				  int numberOfBatches=rs.getInt("numberOfBatches");
				  int courseFee= rs.getInt("courseFee");
				  String courseDuration= rs.getString("courseDuration");
				  
				  cs = new Course(courseid, courseName, session, numberOfBatches, courseFee, courseDuration);
				  
				
			}
			
			else {
				throw new CourseException("courseID NOT Found");
			}
			
		} catch (SQLException e) {
		
		   	throw new CourseException(e.getMessage());
		}
		
		
		
		return cs;
	}

	@Override
	public String allocateAStudentToABatchUnderACourse(int cid, int batchId, int roll) throws CourseException,StudentException,BatchException {
		String message = "Can't be allocated";
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("Select * from course where courseID=?");
			ps1.setInt(1, cid);
			ResultSet rs1= ps1.executeQuery();
			if(rs1.next()) {
				
				PreparedStatement ps2 = conn.prepareStatement("Select * from batches where cid=? AND batchID = ?");
				ps2.setInt(1, cid);
				ps2.setInt(2, batchId);
				ResultSet rs2= ps2.executeQuery();
				if(rs2.next()) {
					
					if(rs2.getInt("numberOfSeatsAvailable")<=0) {
						message = "Seats Not Available";
						return message;
					}
					PreparedStatement ps3 = conn.prepareStatement("Select * from student where roll=?");
					ps3.setInt(1, roll);
					 
					ResultSet rs3= ps3.executeQuery();
					if(rs3.next()) {
						PreparedStatement psCheck = conn.prepareStatement("select * from student_course_allocation where cid=? AND roll=? ");
						
						psCheck.setInt(1, cid);
						psCheck.setInt(2, roll);
						ResultSet rsCheck = psCheck.executeQuery();
						if(rsCheck.next()) {
							message = "Student Already Enrolled in the course with course id :"+cid;
							return message;
						}
						
						
						
						
						PreparedStatement ps4 = conn.prepareStatement("insert into student_course_allocation values(?,?,?)");
						PreparedStatement ps5 = conn.prepareStatement("UPDATE batches Set numberOfSeatsAvailable = numberOfSeatsAvailable-1 where cid=? AND batchID=?");
						
						ps4.setInt(1, cid);
						ps4.setInt(2, roll);
						ps4.setInt(3, batchId);
						
						ps5.setInt(1, cid);
						ps5.setInt(2, batchId);
						
						ps5.executeUpdate();
						int u =ps4.executeUpdate();
						
						if(u>0) {
							message = "Student Allocated Successfully";
						}
					}
					else {
						throw new StudentException("Student not found with roll"+roll);
					}
					
				}
				else {
					throw new BatchException("Batch not found with provided batchID and courseID");
				}
				
				
				
				
			}
			else {
				throw new CourseException("Course not found with courseID "+cid);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CourseException(e.getMessage());
		}
		return message;
	}

}
