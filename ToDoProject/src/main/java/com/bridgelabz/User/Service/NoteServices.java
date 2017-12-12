package com.bridgelabz.User.Service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bridgelabz.User.model.Note;

@Service
public interface NoteServices {
	
	public int saveNotes(Note note) ;
	public void deleteNote(int noteId);
	public void updateNote(Note updatedNote);
	public List<Note> getallNotes();
	public boolean updateArchive(int noteId);
	public void UpdateNoteToTrash(int noteId,boolean isEmptyTrash);
	public void updatePin(int noteId, boolean isPinned);
}
