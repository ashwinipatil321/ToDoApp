package com.bridgelabz.User.Service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.bridgelabz.User.model.Collaborator;
import com.bridgelabz.User.model.Note;
import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;

@Service
public interface NoteServices {
	
	public int saveNotes(Note note) ;
	public void deleteNote(int noteId);
	public void updateNote(Note updatedNote);
	public List<Note> getAllNotes(User user);
	public boolean updateArchive(int noteId);
	public boolean updateTrash(int NoteId);
	public void updatePin(int noteId, boolean isPinned);
	public Note getNoteById(int noteId);
	public void addLabel(NoteLabel label);
	NoteLabel getLabelByName(String labelName);
	public List<NoteLabel> getLabels(User user);
	public boolean deleteLabelById(int id);
	boolean editLabel(NoteLabel label);
	public List<User> getListOfUser(int noteId) ;
	public int saveCollborator(Collaborator collborate);
	public int removeCollborator(int shareWith, int noteId);
	public List<Note> getCollboratedNotes(int userId);
	public void deleteScheduleNote();
	
}

