package com.studyon.dao;

import java.util.List;

import com.studyon.exceptions.BatchException;
import com.studyon.exceptions.CourseException;
import com.studyon.exceptions.StudentException;
import com.studyon.model.Course;

public interface CourseDao {
	
	public List<Course> getAllcoursedetails() throws CourseException;
	
	public String addACourse(String courseName, String session,int courseFee,String courseDuration) throws CourseException;
	
	public String updateFeeOfACourse(int cid, int newAmount) throws CourseException;
	
	public String deleteACourse(int cid) throws CourseException;
	
	public Course searchACourse(int cid) throws CourseException;
	
	public String allocateAStudentToABatchUnderACourse(int cid, int batchId, int roll )throws CourseException, StudentException, BatchException;
	
	
}
