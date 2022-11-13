package com.studyon.model;

public class CourseDetails {
	private int batchID;
	
	private int courseid;
	private String courseName;
	private String session;
	private int courseFee;
	
	private int noOfSeatsAvailable;
	
	public CourseDetails() {
		// TODO Auto-generated constructor stub
	}

	public CourseDetails(int batchID, int courseid, String courseName, String session, int courseFee,int noOfSeatsAvailable) {
		super();
		this.batchID = batchID;
		this.courseid = courseid;
		this.courseName = courseName;
		this.session = session;
		this.courseFee = courseFee;
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public int getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}

	public int getNoOfSeatsAvailable() {
		return noOfSeatsAvailable;
	}

	public void setNoOfSeatsAvailable(int noOfSeatsAvailable) {
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}

	@Override
	public String toString() {
		return "CourseDetails [batchID=" + batchID + ", courseid=" + courseid + ", courseName=" + courseName
				+ ", session=" + session + ", courseFee=" + courseFee + ", noOfSeatsAvailable=" + noOfSeatsAvailable
				+ "]";
	}
	
	
	
	

}
