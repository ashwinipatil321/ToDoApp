package com.bridgelabz.User.DAO;
import java.util.List;
import java.util.logging.LogManager;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.User.model.Note;
import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;

@Repository
public class NoteDaoImplemention implements NoteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	NoteDAO noteDAO;


	public int saveNotes(Note note) {

		Session session = sessionFactory.getCurrentSession();
		int id=	(int) session.save(note);
		return id;
	}

	@Override
	public void deleteNote(int noteId) {

		Session session = sessionFactory.getCurrentSession();
		Note persistedNote = session.load(Note.class, noteId);
		if (persistedNote != null) {
			session.delete(persistedNote);
		}
	}

	@Override
	public void updateNote(Note note) {
		System.out.println("noteId=" + note.getNoteId() + ", title=" + note.getTitle() + ", description=" + note.getDescription() + ", createdDate="+ note.getCreatedDate() + ", modifiedDate=" + note.getModifiedDate()+"reminder"+note.getReminder());
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(note);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getallNotes() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Note ");
		List<Note> ls = query.getResultList();
		return ls;
	}
	@Override
	public Note getNoteById(int noteId) {
		Session session = sessionFactory.getCurrentSession();
		return session.load(Note.class, noteId);
	}

	@Override
	public boolean updateArchive(int noteId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery( "UPDATE com.bridgelabz.User.model.Note set isArchive=:isArchive WHERE noteId = :noteId");
		query.setParameter("noteId", noteId);
		query.setParameter("isArchive", true);
		query.executeUpdate();
		System.out.println("query executed successfully...");
		return true;
	}

	@Override
	public boolean updateTrash(int noteId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery( "UPDATE com.bridgelabz.User.model.Note set emptyTrash=:emptyTrash WHERE noteId = :noteId");
		query.setParameter("noteId", noteId);
		query.setParameter("emptyTrash", true);
		query.executeUpdate();
		System.out.println("query executed successfully...");
		return true;
	}

	@Override
	public void addLabel(NoteLabel label) {

		Session session=sessionFactory.getCurrentSession();
		session.save(label);

	}

	@Override
	public 	NoteLabel getLabelByName(String labelName)
	{
		Session session = sessionFactory.getCurrentSession();
		NoteLabel objLabel = null;
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(NoteLabel.class);
		criteria.add(Restrictions.eq("labelName", labelName));
		objLabel = (NoteLabel) criteria.uniqueResult();
		return objLabel;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<NoteLabel> getLabels(User user) {

		Session session = sessionFactory.getCurrentSession();
		// transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(NoteLabel.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		List<NoteLabel> labels = criteria.list();
		return labels;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean deletelabelById(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NoteLabel.class);
		criteria.add(Restrictions.eq("id", id));
		NoteLabel labels = (NoteLabel) criteria.uniqueResult();
		session.delete(labels);
		return true;
	}

	@Override
	public boolean editLabel(NoteLabel label) {

		Session session = sessionFactory.getCurrentSession();
		session.update(label);			
		return true;

	} 
}


