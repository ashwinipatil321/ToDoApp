package com.bridgelabz.User.DAO;

import com.bridgelabz.User.model.User;

/**
 * @author Ashwini todoApp
 *
 */
public interface UserDao {
	

	/**
	 * @param user
	 * @return
	 * register the user by user object
	 */
	public int registration(User userDetails) ;
	

	/**
	 * @param user
	 * @return
	 * check the login user valid or not at the login time
	 */
	public User login(User userDetails);
	
	
	/**
	 * @param email
	 * @return
	 * check the email id is already exist or not
	 */
	public User getUserByEmail(String email);
	
	/**
	 * @param id
	 * @return
	 * check the  id is already exist or not
	 */
	public 	User getUserById(int id);
	
	/**
	 * @param id
	 * @return
	 * update the user activation
	 */
	public boolean updateActivation(int id);
	
	/**
	 * @param id
	 * @return
	 * update the user
	 */
	public void updateUser(User user);

}
