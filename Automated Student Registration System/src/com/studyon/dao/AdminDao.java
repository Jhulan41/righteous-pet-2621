package com.studyon.dao;

import com.studyon.exceptions.AdminException;

public interface AdminDao {
	
	public boolean checkAdminAuthenticity(String username, String password)throws AdminException;

}
