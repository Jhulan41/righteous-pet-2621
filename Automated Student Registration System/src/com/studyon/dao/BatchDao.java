package com.studyon.dao;

import java.util.List;

import com.studyon.exceptions.BatchException;
import com.studyon.exceptions.CourseException;
import com.studyon.exceptions.StudentException;
import com.studyon.model.Batch;
import com.studyon.model.Course;
import com.studyon.model.CourseDetails;
import com.studyon.model.Student;

public interface BatchDao {
	
	public String createBatch(int cid, int noOfSeats) throws BatchException;
	
	public List<Batch> getAllbatchdetails() throws BatchException;
	
	public List<Student> StudentFromAbatch(int batchID) throws StudentException;

}
