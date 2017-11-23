package com.bridgelabz.User.Service;

import com.bridgelabz.User.model.User;

public interface UserService {
	
	public boolean createUser(User user);
	public String loginUser(User user);
	public User getUserByEmail(String email);
	public User getUserById(int id);
	public boolean updateActivation(int id);
}


