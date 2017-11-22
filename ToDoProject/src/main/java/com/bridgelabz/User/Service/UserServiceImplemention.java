package com.bridgelabz.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.User.DAO.UserDao;
import com.bridgelabz.User.model.User;

@Service
@Transactional
public class UserServiceImplemention implements UserService {

	@Autowired
	UserDao userDao;

	public boolean createUser(User user) {
		boolean found = userDao.registration(user);
		if (found)
			return found;
		else
			return found;
	}

	public String loginUser(User user) {
		return userDao.login(user);
	}
}
