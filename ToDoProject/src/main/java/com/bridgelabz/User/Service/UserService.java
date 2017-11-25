package com.bridgelabz.User.Service;

import com.bridgelabz.User.model.User;

public interface UserService {
	
	public int createUser(User user);
	public User loginUser(User user);
	public  User getUserByEmail(String email) ;
	public User getUserById(int id);
	public boolean updateActivation(int id);
	public void updateUser(User user);

}


