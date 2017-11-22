package com.bridgelabz.User.Utility;

import com.bridgelabz.User.model.User;

public class Validation {
	static String regexName = "[a-zA-Z\\s]{4,}";
	static String mobileNumberRegx = "^[0-9]{10}$";
	static String regexEmail = "[a-z0-9]{1,}[@]{1}[a-z]{1,}[.]{1}[a-z]{1,}";

	public static boolean isvalidation(User user) {

		if (!user.getUserFirstName().matches(regexName) && !user.getUserLastName().matches(regexName)) {
			return false;
		} else if (!user.getMobileNumber().matches(mobileNumberRegx)) {
			return false;
		}

		else if (!user.getEmail().matches(regexEmail)) {
			return false;
		} else if (!(user.getPassword().length() < 6)) {
			return false;
		}
		return true;
	}
	public static boolean emailValidtaion(User user) {
		if (!user.getEmail().matches(regexEmail))
			return false;
		else
			return true;
	}
}
