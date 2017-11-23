package com.bridgelabz.User.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.User.Service.UserService;
import com.bridgelabz.User.Utility.SendMail;
import com.bridgelabz.User.Utility.Token;
import com.bridgelabz.User.Utility.Validation;
import com.bridgelabz.User.model.User;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	SendMail sendMail;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registrationUser(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		String email = user.getEmail();
		boolean found = userService.createUser(user);

		User user1 = userService.getUserByEmail(email);
		System.out.println(user1.getUserID());

				String to = email;
		String msg = "click link to verfiy your account ";
		String subject = "Verfiy Mail";

			boolean valid = Validation.isvalidation(user);
			if (!valid) {
				if (found) {
					String url = request.getRequestURL().toString();
					String token = Token.generateToken(email, user1.getUserID());
					url=url.substring(0, url.lastIndexOf('/')) + "/activate/"+ token;

					response.setHeader("register", token);
					System.out.println("hiiii"+to);
					System.out.println("hellow"+subject);
					System.out.println("hum"+msg);


					sendMail.sendMail(to, subject, msg,url);
					System.out.println(Token.verify(token));
					return new ResponseEntity<String>("Register Sucessfully...", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("EmailId and password Aready Existe...", HttpStatus.BAD_REQUEST);
				}
			}
		return new ResponseEntity<String>("Invalid credential", HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletResponse response) {
		String email = user.getEmail();
		boolean valid = Validation.emailValidtaion(user);
		if (valid) {
			String name = userService.loginUser(user);

			if (name != null) {
				String token = Token.generateToken(email, user.getUserID());

				response.setHeader("login", token);

				System.out.println(Token.verify(token));
				return new ResponseEntity<String>("login successful!!!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("EmailId and password is invalid..", HttpStatus.BAD_REQUEST);
			}
		} else {

			return new ResponseEntity<String>("Invalid credential", HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/activate/{jwt:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> activate(@PathVariable("jwt") String jwt, HttpSession session, HttpServletRequest request) {
		System.out.println(jwt);

		int id = Token.verify(jwt);
		if (id > 0) {
			User user = userService.getUserById(id);
			if (user != null) {
				Boolean status = userService.updateActivation(id);
				if(status==true)
				return new ResponseEntity<String>("Activation done", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("user not valid!!!", HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<String> logout(HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("token");  
		return new ResponseEntity<String>("Logout done", HttpStatus.OK);
	}
}
