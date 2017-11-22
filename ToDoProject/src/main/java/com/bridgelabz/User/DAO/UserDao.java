package com.bridgelabz.User.DAO;

import com.bridgelabz.User.model.User;

public interface UserDao {
	public boolean registration(User userDetails) ;
	public String login(User userDetails);

}
