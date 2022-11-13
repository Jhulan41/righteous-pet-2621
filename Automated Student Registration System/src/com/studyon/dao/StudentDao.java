package com.studyon.dao;

 
import java.util.List;

import com.studyon.exceptions.StudentException;
import com.studyon.model.CourseDetails;
import com.studyon.model.Student;

public interface StudentDao {

	public Student checkStudentAuthenticity(String username, String password)throws StudentException;
	
	public String studentSignUp(String name, String address, String email, String password)throws StudentException;
	
	public String updateStudentName(Student s,String newName) throws StudentException;
	public String updateStudentAddress(Student s,String newAddress) throws StudentException;
	public String updateStudentEmail(Student s,String newEmail) throws StudentException;
	public String updateStudentPassword(Student s,String newPass) throws StudentException;
	
	public String studentRegistration(Student s,int courseID, int batchID) throws StudentException;
	
	public List<CourseDetails> courseDetails();
	
}
