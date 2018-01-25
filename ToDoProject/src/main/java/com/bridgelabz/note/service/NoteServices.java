package com.bridgelabz.note.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bridgelabz.User.model.Collaborator;
import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;
import com.bridgelabz.note.modal.Note;

/**
 * @author bridgeit
 *
 */
@Service
public interface NoteServices {
	
	/**
	 * @param note
	 * @return add note
	 */
	public int saveNotes(Note note) ;
	
	/**
	 * @param noteId
	 * delete Note
	 */
	public void deleteNote(int noteId);
	
	/**
	 * @param updatedNote
	 * update Note
	 */
	public void updateNote(Note updatedNote);
	
	
	/**
	 * @param user
	 * @return all notes
	 */
	public List<Note> getAllNotes(User user);
	
	
	/**
	 * @param noteId
	 * @return add to arcieve
	 */
	public boolean updateArchive(int noteId);
	
	
	/**
	 * @param NoteId
	 * @return add to trass
	 */
	public boolean updateTrash(int NoteId);
	
	/**
	 * @param noteId
	 * @param isPinned
	 */
	public void updatePin(int noteId, boolean isPinned);
	/**
	 * @param noteId
	 * @return note by id
	 */
	public Note getNoteById(int noteId);
	
	
	/**
	 * @param label
	 * add labels
	 */
	public void addLabel(NoteLabel label);
	
	/**
	 * @param labelName
	 * @return label
	 */
	NoteLabel getLabelByName(String labelName);
	
	/**
	 * @param user
	 * @return labels
	 */
	public List<NoteLabel> getLabels(User user);
	
	/**delete
	 * @param id
	 * @return
	 */
	public boolean deleteLabelById(int id);
	
	/**
	 * @param label
	 * @return
	 */
	boolean editLabel(NoteLabel label);
	/**
	 * @param noteId
	 * @return
	 */
	public List<User> getListOfUser(int noteId) ;
	/**
	 * @param collborate
	 * @return
	 */
	public int saveCollborator(Collaborator collborate);
	/**
	 * @param shareWith
	 * @param noteId
	 * @return
	 */
	public int removeCollborator(int shareWith, int noteId);
	/**
	 * @param userId
	 * @return
	 */
	public List<Note> getCollboratedNotes(int userId);
	/**
	 * delete Schedule Note
	 */
	public void deleteScheduleNote();
	
}

