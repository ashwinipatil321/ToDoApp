package com.bridgelabz.User.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String password;
	private String email;
	private String mobileNumber;
	private boolean activated;
	
	@Lob
	@Column(name="profile_url",columnDefinition="LONGBLOB")
	private String profileUrl;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<NoteLabel> alLabels;

	public List<NoteLabel> getAlLabels() {
		return alLabels;
	}
	public void setAlLabels(List<NoteLabel> alLabels) {
		this.alLabels = alLabels;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	@Column(name = "userFirstName")
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	@Column(name = "userLastName")
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	@Column(name = "userPassword")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	@Column(name = "Email")
	public String getEmail() {
		return email;
	}
	
	@Column(name = "id", length = 11 )
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MobileNumber")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "isActivated")
	public boolean getActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		activated = this.activated;
	}
	
	@OneToMany(fetch = FetchType.EAGER ,cascade =CascadeType.ALL)
	public List<Note> getNotes() {
		return null;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", password=" + password + ", email=" + email + ", mobileNumber=" + mobileNumber + ", activated="
				+ activated + ", profileUrl=" + profileUrl + "]";
	}
	
}
