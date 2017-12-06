package com.bridgelabz.User.Controller;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.User.Service.NoteServices;
import com.bridgelabz.User.Service.UserService;
import com.bridgelabz.User.Utility.Token;
import com.bridgelabz.User.model.CustomeResponse;
import com.bridgelabz.User.model.Note;
import com.bridgelabz.User.model.User;

@RestController
public class NoteController {

	@Autowired
	private NoteServices noteService;

	@Autowired
	private UserService UserService;

	CustomeResponse myResponse = new CustomeResponse();

	@RequestMapping(value = "user/saveNote", method = RequestMethod.POST)
	public ResponseEntity<CustomeResponse> saveNote(@RequestBody Note note, HttpServletRequest request,
			HttpServletResponse response) {
System.out.println("inside save");
		String token = request.getHeader("token");
		int id = Token.verify(token);
		User user = UserService.getUserById(id);

		if (user != null) {

			if (id > 0) {

				boolean isActive = user.getActivated();

				if (isActive ==true && (note.getTitle().length() > 0 || note.getDescription().length() > 0)) {
					 System.out.println("inside notes....");
					note.setUser(user);
					CustomeResponse myResponse = new CustomeResponse();
					Date date = new Date(System.currentTimeMillis());
					note.setCreatedDate(date);
					note.setModifiedDate(date);
					noteService.saveNotes(note);
					myResponse.setMessage("Note is added");
					myResponse.setStatus(1);
					return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);
				}

				myResponse.setMessage("User not Activated ");
				myResponse.setStatus(1);
				return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);

			} else

				myResponse.setMessage("Token issue");
			myResponse.setStatus(1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);

		}
		myResponse.setMessage("Invalid!!!\n Login To Continue");
		myResponse.setStatus(1);
		return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "user/deleteNote", method = RequestMethod.DELETE)
	public ResponseEntity<CustomeResponse> deleteNote(@RequestBody Note note,HttpServletRequest request) {
		System.out.println("id in delete note........"+note.getNoteId());
		String token = request.getHeader("token");
		int id = Token.verify(token);
		User user = UserService.getUserById(id);

		if (user != null) {

			noteService.deleteNote(note.getNoteId());
			myResponse.setMessage("Note is deleted");
			myResponse.setStatus(1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);
		}

		else{

			myResponse.setMessage("Note is Notdeleted");
			myResponse.setStatus(-1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ResponseEntity<CustomeResponse> updateNote(@RequestBody Note note,HttpServletRequest request) {

		String token = request.getHeader("token");
		int id = Token.verify(token);
		User user = UserService.getUserById(id);

		if (user != null) {

			noteService.updateNote(note);
			myResponse.setMessage("Note is updated");
			myResponse.setStatus(1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);
		}
		else {

			myResponse.setMessage("Note is not Updated");
			myResponse.setStatus(-1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/user/getAllNotes", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getAllNotes(HttpServletRequest request) {

		String token = request.getHeader("token");
		System.out.println("hiiiiiiiiii"+token);
		int id = Token.verify(token);
		System.out.println(id);
		User user = UserService.getUserById(id);
		List<Note> allNotes = null;

		if (user != null) {

			allNotes = noteService.getallNotes();
			System.out.println(allNotes);
			myResponse.setMessage("Got all the notes");
			myResponse.setStatus(1);
			return new ResponseEntity<List<Note>>(allNotes, HttpStatus.OK);
		}

		else {

			myResponse.setMessage("Got all the notes");
			myResponse.setStatus(1);
			return new ResponseEntity<List<Note>>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/isArchive/{noteId}", method = RequestMethod.POST)
	public ResponseEntity<CustomeResponse> updateArchive(@PathVariable("noteId") int noteId ) {
		CustomeResponse myResponse = new CustomeResponse();
		try {
			noteService.updateArchive(noteId);
			myResponse.setMessage("Archive is updated");
			myResponse.setStatus(1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			myResponse.setMessage("Archive is not Updated");
			myResponse.setStatus(-1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/emptyTrash/{noteId}", method = RequestMethod.POST)
	public ResponseEntity<CustomeResponse> updateEmptyTrash( @PathVariable("noteId") int noteId) {
		CustomeResponse myResponse = new CustomeResponse();

		try {

			noteService.updateEmptyTrash(noteId);
			myResponse.setMessage("Trash is Not Empty...");
			myResponse.setStatus(1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			myResponse.setMessage("Trash is Empty");
			myResponse.setStatus(-1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/isPin/{noteId}", method = RequestMethod.POST)
	public ResponseEntity<CustomeResponse> updatePin(@PathVariable("noteId") int noteId) {
		CustomeResponse myResponse = new CustomeResponse();

		try {
			noteService.updatePin(noteId);
			myResponse.setMessage("Pin Updated");
			myResponse.setStatus(1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			myResponse.setMessage("Pin is not Updated");
			myResponse.setStatus(-1);
			return new ResponseEntity<CustomeResponse>(myResponse, HttpStatus.BAD_REQUEST);
		}
	}
}
