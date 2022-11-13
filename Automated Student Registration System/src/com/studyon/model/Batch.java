package com.studyon.model;

public class Batch {
	private int batchID; 
	private int courseID; 
	private int numberOfSeats; 
	
	public Batch() {
		// TODO Auto-generated constructor stub
	}

	public Batch(int batchID, int courseID, int numberOfSeats) {
		super();
		this.batchID = batchID;
		this.courseID = courseID;
		this.numberOfSeats = numberOfSeats;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@Override
	public String toString() {
		return "Batch [batchID=" + batchID + ", courseID=" + courseID + ", numberOfSeats=" + numberOfSeats + "]";
	}
	
	

}
