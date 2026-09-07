package com.pethub.dao;

import java.util.List;

import com.pethub.model.User;

public interface UserDAO {
	
	void addUser(User user);
	User getUser(int userId);
	User getUserByEmail(String email);
	List<User> getAllUsers();
	void updateUser(User user);
	void deleteUser(int userId);
	
	
	

}
