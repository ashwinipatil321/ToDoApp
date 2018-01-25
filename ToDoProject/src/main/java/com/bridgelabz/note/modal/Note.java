package com.bridgelabz.note.modal;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.User.model.NoteLabel;
import com.bridgelabz.User.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author bridgeit
 *
 */
@Entity
@Table(name = "NoteTable")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noteId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "note_label", joinColumns = { @JoinColumn(name = "noteId") }, inverseJoinColumns = {
			@JoinColumn(name = "label_id") })
	private Set<NoteLabel> allLabels = new HashSet<>();

	@Column(name = "note_title")
	private String title;

	@Column(name = "note_description")
	private String description;

	@Column(name = "note_cretedDate")
	private Date createdDate;

	@Column(name = "note_modifiedDate")
	private Date modifiedDate;


	@Column(name = "Is_Reminded", nullable = false)
	private boolean reminder;
	
	@Column(name = "Reminder_Date", nullable = true)
	private Date reminderDate;

	@Column(name = "Reminder_Time", nullable = true)
	private String reminderTime;
	
	@Column(name = "isArchive")
	private boolean isArchive;

	@Column(name = "emptyTrash")
	private boolean emptyTrash;

	@Column(name = "isPin")
	private boolean isPin;

	@Column(name = "colors")
	private String color;

	@Lob
	@Column(name = "noteImage", columnDefinition = "LONGBLOB")
	private String noteImage;

	
	
	@JsonIgnore
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private User user;

	/**
	 * @return NoteId
	 */
	public int getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	
	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return description on notes
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return note Image
	 */
	public String getNoteImage() {
		return noteImage;
	}

	/**
	 * @param noteImage
	 */
	public void setNoteImage(String noteImage) {
		this.noteImage = noteImage;
	}

	/**
	 * @return create date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return modified date
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	
	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return isArchive
	 */
	public boolean isArchive() {
		return isArchive;
	}

	/**
	 * @param isArchive
	 */
	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	/**
	 * @return is Empty Trash
	 */
	public boolean isEmptyTrash() {
		return emptyTrash;
	}

	/**
	 * @param emptyTrash
	 */
	public void setEmptyTrash(boolean emptyTrash) {
		this.emptyTrash = emptyTrash;
	}

	/**
	 * @return isPin
	 */
	public boolean isPin() {
		return isPin;
	}

	/**
	 * @param isPin
	 */
	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return labels
	 */
	public Set<NoteLabel> getAllLabels() {
		return allLabels;
	}

	/**
	 * @param allLabels
	 */
	public void setAllLabels(Set<NoteLabel> allLabels) {
		this.allLabels = allLabels;
	}

	/**
	 * @return reminder date
	 */
	public Date getReminderDate() {
		return reminderDate;
	}

	/**
	 * @param reminderDate
	 */
	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}

	/**
	 * @return reminder time
	 */
	public String getReminderTime() {
		return reminderTime;
	}

	/**
	 * @param reminderTime
	 */
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	
	/**
	 * @return isReminder
	 */
	public boolean isReminder() {
		return reminder;
	}

	/**
	 * @param reminder
	 */
	public void setReminder(boolean reminder) {
		this.reminder = reminder;
	}


	/**
	 * @param copy note
	 */
	public void copy(Note note) {

		this.color = note.getColor();
		this.description = note.getDescription();
		this.emptyTrash = note.isEmptyTrash();
		this.isArchive = note.isArchive();
		this.isPin = note.isPin();
		this.modifiedDate = note.getModifiedDate();
		this.title = note.getTitle();
	}
}

