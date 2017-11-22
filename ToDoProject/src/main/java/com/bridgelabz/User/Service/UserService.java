package com.bridgelabz.User.Service;

import com.bridgelabz.User.model.User;

public interface UserService {
	
	public boolean createUser(User user);
	public String loginUser(User user);
}


