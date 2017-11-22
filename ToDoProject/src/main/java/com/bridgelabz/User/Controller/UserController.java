package com.bridgelabz.User.Controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.User.Service.UserService;
import com.bridgelabz.User.Utility.SendMail;
import com.bridgelabz.User.Utility.Validation;
import com.bridgelabz.User.model.User;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registrationUser(@RequestBody User user, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String to = user.getEmail();
		String msg = "click link to verfiy your account" + url;
		String subject = "Verfiy Mail";
		if (user.getEmail() != null && user.getUserFirstName() != null && user.getUserFirstName() != null
				&& user.getMobileNumber() != null && user.getPassword() != null) {
			boolean valid = Validation.isvalidation(user);
			if (!valid) {
				boolean found = userService.createUser(user);
				if (found) {
					SendMail.sendMail(to, subject, msg);
					return new ResponseEntity<String>("Register Sucessfully...", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("EmailId and password Aready Existe...", HttpStatus.BAD_REQUEST);
				}
			}
		}
		return new ResponseEntity<String>("Invalid credential", HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody User user) {

		boolean valid = Validation.emailValidtaion(user);
		if (valid) {
			String name = userService.loginUser(user);
			if (name != null) {
				return new ResponseEntity<String>("login successful!!!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("EmailId and password is invalid..", HttpStatus.BAD_REQUEST);
			}

		} else {

			return new ResponseEntity<String>("Invalid credential", HttpStatus.CONFLICT);
		}
	}
}
