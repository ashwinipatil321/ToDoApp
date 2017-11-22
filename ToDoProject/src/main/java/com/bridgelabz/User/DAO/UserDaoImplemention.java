package com.bridgelabz.User.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.User.model.User;

@Repository
public class UserDaoImplemention implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public boolean registration(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("from User where email =:email or mobileNumber = :phone");
		query.setParameter("email", user.getEmail());
		query.setParameter("phone", user.getMobileNumber());
		List<?> list = query.list();
		if (list != null) {
			session.save(user);
			return true;
		} else {
			return false;
		}
	}

	public String login(User user) {
		Session session = sessionFactory.getCurrentSession();

		Query<?> query = session
				.createQuery("select userFirstName from User where email =:email and password =:password");
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		String name = (String) query.uniqueResult();

		if (name != null) {
			return name;
		}
		return null;
	}
}
