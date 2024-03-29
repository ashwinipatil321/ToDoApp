package com.bridgelabz.note.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.User.model.Collaborator;
import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;
import com.bridgelabz.note.modal.Note;

/**
 * @author Ashwini
 *
 */

@Service
public interface NoteDAO {

	/** 
	 * @param note
	 * @return
	 * add notes
	 */
	public int saveNotes(Note note) ;
	
	
	/**
	 * @param noteId
	 * delete notes
	 */
	public void deleteNote(int noteId);
	
	
	/**
	 * @param updatedNote
	 * 
	 */
	public void updateNote(Note updatedNote);
	

	/**
	 * @param getAllNotes
	 * 
	 */
	public List<Note> getAllNotes(User user);	
	
	
	/**
	 * @param noteId
	 * @return
	 */
	public Note getNoteById(int noteId);
	
	/**
	 * @param noteId
	 * @return
	 */
	public boolean updateArchive(int noteId);
	
	/**
	 * @param noteId
	 * @return
	 */
	public boolean updateTrash(int noteId);
	
	
	/**
	 * @param label
	 */
	public void addLabel(NoteLabel label);
	
	
	/**
	 * @param labelName
	 * @return
	 */
	public NoteLabel getLabelByName(String labelName);
	
	
	/**
	 * @param user
	 * @return
	 */
	public List<NoteLabel> getLabels(User user);
	
	
	/**
	 * @param id
	 * @return
	 */
	public boolean deletelabelById(int id);
	
	
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
	 * deleteScheduleNote
	 */
	public void deleteScheduleNote();
	
}