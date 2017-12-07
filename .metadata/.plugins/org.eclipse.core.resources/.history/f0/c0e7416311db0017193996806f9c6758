package com.bridgelabz.User.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private int UserID;
	private String userFirstName;
	private String userLastName;
	private String password;
	private String email;
	private String mobileNumber;
	private boolean activated;
	private String profileUrl;
	
	@Column(name="profile_url")
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

	@Column(name = "id", length = 11 )
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}

	@Column(name = "Email")
	public String getEmail() {
		return email;
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
	@Override
	public String toString() {
		return "UserDetails [UserID=" + UserID + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", password=" + password + ", email=" + email + ", mobileNumber=" + mobileNumber + ", Activated="
				+ activated + "]";
	}
	public List<Note> getNotes() {
		return null;
	}
}
