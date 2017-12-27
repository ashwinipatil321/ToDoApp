package com.bridgelabz.User.Service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.User.DAO.NoteDAO;
import com.bridgelabz.User.model.Note;
import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;

@Service
public class NoteServiceImplemention implements NoteServices {

	@Autowired
	private NoteDAO noteDao;

	@Transactional
	public int saveNotes(Note note) {
		int id=noteDao.saveNotes(note);
		return id;
	}
	
	@Transactional
	public void deleteNote(int noteId) {
		noteDao.deleteNote(noteId);
	}

	@Transactional
	public void updateNote(Note note) {
		
		 Note oldNote=noteDao.getNoteById(note.getNoteId());
		 System.out.println("note update "+note.getTitle()+" "+note.getDescription());
		 oldNote.setTitle(note.getTitle());
		 oldNote.setDescription(note.getDescription());
		 oldNote.setColor(note.getColor());
		 oldNote.setReminder(note.getReminder());
		 oldNote.setArchive(note.isArchive());
		 oldNote.setEmptyTrash(note.isEmptyTrash());
		 oldNote.setNoteImage(note.getNoteImage());
		 noteDao.updateNote(oldNote);
	}

	@Transactional
	public List<Note> getallNotes() {

		return noteDao.getallNotes();
	} 

	@Transactional
	public Note getNoteById(int noteId) {
		return noteDao.getNoteById(noteId);
	}

	@Transactional
	public boolean updateArchive(int NoteId)
	{
		noteDao.updateArchive(NoteId);
		return true;
	}

	@Transactional
	public boolean updateTrash(int NoteId)
	{
		noteDao.updateTrash(NoteId);
		return true;
	}


	@Transactional
	public void updatePin(int noteId, boolean isPinned)
	{
		Note note = noteDao.getNoteById(noteId);
		note.setPin(isPinned);
		noteDao.updateNote(note);
	}

	@Transactional
	public void addLabel(NoteLabel label) {

		noteDao.addLabel(label);
	}

	@Transactional
	public NoteLabel getLabelByName(String labelName)
	{
		return noteDao.getLabelByName(labelName);
	}

	@Transactional
	public List<NoteLabel> getLabels(User user)
	{
		return noteDao.getLabels(user);
	}

	@Transactional
	public boolean deleteLabelById(int id) {
		noteDao.deletelabelById(id);
		return true;
	}
	
	@Transactional
	public boolean editLabel(NoteLabel label) {
		noteDao.editLabel(label);
		return true;
	}
}
