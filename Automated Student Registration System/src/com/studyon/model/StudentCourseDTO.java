package com.studyon.model;

public class StudentCourseDTO {
	
	private int courseID;
	
	private int batchID;
	
	private int roll;
	
	
	public StudentCourseDTO() {
		// TODO Auto-generated constructor stub
	}

	public StudentCourseDTO(int courseID, int batchID, int roll) {
		super();
		this.courseID = courseID;
		this.batchID = batchID;
		this.roll = roll;
	}
	
	

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	@Override
	public String toString() {
		return "StudentCourseDTO [courseID=" + courseID + ", batchID=" + batchID + ", roll=" + roll + "]";
	}
	 
	

}
