package com.studyon.model;

public class Course {
	

	private int cid;
	private String courseName;
	private String session;
	private int numberOfBatches;
	private Double courseFee;
	private String courseDuration;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(int cid, String courseName, String session, int numberOfBatches, Double courseFee,
			String courseDuration) {
		super();
		this.cid = cid;
		this.courseName = courseName;
		this.session = session;
		this.numberOfBatches = numberOfBatches;
		this.courseFee = courseFee;
		this.courseDuration = courseDuration;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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

	public int getNumberOfBatches() {
		return numberOfBatches;
	}

	public void setNumberOfBatches(int numberOfBatches) {
		this.numberOfBatches = numberOfBatches;
	}

	public Double getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	@Override
	public String toString() {
		return "Course [cid=" + cid + ", courseName=" + courseName + ", session=" + session + ", numberOfBatches="
				+ numberOfBatches + ", courseFee=" + courseFee + ", courseDuration=" + courseDuration + "]";
	}
	
	

}
