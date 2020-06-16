package com.bway.springproject.dao;

import com.bway.springproject.models.User;

public interface UserDao {
	
	void signup(User user);
	boolean login(String un,String psw);
}
