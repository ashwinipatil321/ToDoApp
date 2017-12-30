package com.bridgelabz.User.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Collaborator {
	
	@Id
	@GenericGenerator(name="col", strategy="increment")
	@GeneratedValue(generator="col")
	private int collaboratorId;
	
	
	@ManyToOne
	@JoinColumn(name="noteId")
	private Note noteId;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	 private User ownerId;
	
	@ManyToOne
	@JoinColumn(name="shareId")
	 private User shareId;

	public int getCollaboratorId() {
		return collaboratorId;
	}

	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public Note getNoteId() {
		return noteId;
	}

	public void setNoteId(Note noteId) {
		this.noteId = noteId;
	}

	public User getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}

	public User getShareId() {
		return shareId;
	}

	public void setShareId(User shareId) {
		this.shareId = shareId;
	}
}
