package com.bridgelabz.User.DAO;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.User.model.Note;

@Service
public interface NoteDAO {
	
	public int saveNotes(Note note) ;
	public void deleteNote(int noteId);
	public void updateNote(Note updatedNote);
	public List<Note> getallNotes();
	public Note getNoteById(int noteId);
	public boolean updateArchive(int noteId);
}