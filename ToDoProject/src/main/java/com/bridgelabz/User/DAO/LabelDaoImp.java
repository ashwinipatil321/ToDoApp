/*package com.bridgelabz.User.DAO;


import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.User.model.Note;
import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;



@Repository
public class LabelDaoImp implements LabelDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	User user;
	
	@Override
	public void addLabel(NoteLabel label) {
		
		Session session=sessionFactory.getCurrentSession();
		session.save(label);
		
		}
	
	public boolean updateLable(NoteLabel label) {
			
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(label);
		}
		
	
	@Override
	    public boolean deleteLable(NoteLabel label) {
			
		Session session = sessionFactory.getCurrentSession();
		Note persistedNote = session.load(NoteLabel.class, labelId);
		if (persistedNote != null) {
			session.delete(persistedNote);
		}	
		}

		public Set<NoteLabel> getAllLabels(int userId) {
			
			Session session=sessionFactory.getCurrentSession();
			User user=session.get(User.class,userId);
			Set<NoteLabel> label=user.get();
			System.out.println(label);
			session.close();
			return label;
		}
		
		
		

		@Override
		public List<User> getUserList() {
		
	  
		}

	
	
	@Override
	public Set<NoteLabel> getAllLabels(int userId) {
		
		Session session=sessionFactory.getCurrentSession();
		User user=(User) session.get(User.class,userId);
		Set<NoteLabel> label=user.();
		System.out.println(label);
		session.close();
		return label;
	}
}
*/