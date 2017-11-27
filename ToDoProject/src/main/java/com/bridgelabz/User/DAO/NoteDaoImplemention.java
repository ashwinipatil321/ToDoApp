package com.bridgelabz.User.DAO;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bridgelabz.User.model.Note;

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
	public void updateNote(Note updatedNote) {

		String hql = "UPDATE com.bridgelabz.User.model.Note set note_title=:title,note_description=:description,note_cretedDate=:noteCreatedDate,note_modifiedDate=:noteEditedDate WHERE noteId = :noteid";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("title", updatedNote.getTitle());
		query.setParameter("description", updatedNote.getDescription());
		query.setParameter("noteCreatedDate", updatedNote.getCreatedDate());
		query.setParameter("noteEditedDate", updatedNote.getModifiedDate());
		query.setParameter("noteid", updatedNote.getNoteId());

		 query.executeUpdate();
		System.out.println("query executed successfully...");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getallNotes() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM com.bridgelabz.User.model.Note ");
		List<Note> ls = query.getResultList();
		return ls;
	}
	@Override
	public Note getNoteById(int noteId) {
		Session session = sessionFactory.openSession();
		return session.load(Note.class, noteId);
	}
	
	@Override
	public boolean updateArchive(int noteId)
	{
		Session session = sessionFactory.getCurrentSession();
		//Boolean isArchive = (Boolean) session.createQuery("select isArchive from Note where  noteId =:noteId").uniqueResult();
		
		Query query = session.createQuery( "UPDATE com.bridgelabz.User.model.Note set isArchive=:isArchive WHERE noteId = :noteid");
		query.setParameter("noteId", noteId);
		query.setParameter("isArchive", true);
		query.executeUpdate();
		System.out.println("query executed successfully...");
		return true;
		/*if(isArchive == false)
		{
		query.setParameter("isArchive", true);
		query.executeUpdate();
		System.out.println("query executed successfully...");
		return true;
		}
		
		else{
			
			query.setParameter("isArchive", false);
			query.executeUpdate();
			System.out.println("query executed successfully...");
			return true;
		}*/
	}
	
	@Override
	public boolean updateEmptyTrash(int UserId)
	{
		
		String hqlQuery = "UPDATE com.bridgelabz.User.model.Note set emptyTrash=:emptyTrash WHERE UserId = :UserId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("userId", UserId);
		query.setParameter("emptyTrash", true);
		query.executeUpdate();
		System.out.println("query executed successfully...");
		return true;
	}
	
	@Override
	public boolean updatePin(int noteId)
	{
		
		String hqlQuery = "UPDATE com.bridgelabz.User.model.Note set isPin=:isPin WHERE NoteID = :NoteID";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("noteId", noteId);
		query.setParameter("isPin", true);
		query.executeUpdate();
		System.out.println("query executed successfully...");
		return true;
	}
}
