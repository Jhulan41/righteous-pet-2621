package com.studyon.usecases;

import java.sql.Connection;
import java.sql.SQLException;

import com.studyon.dbutility.DBUtil;

public class Check {

	public static void main(String[] args) {
		try (Connection conn = DBUtil.getConnection()){
			System.out.println("Success");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
