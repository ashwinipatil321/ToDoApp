package com.bridgelabz.User.Controller;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.User.Service.UserService;
import com.bridgelabz.User.SocialLogin.GoogleConnection;
import com.bridgelabz.User.Utility.Token;
import com.bridgelabz.User.model.Response;
import com.bridgelabz.User.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GoogleLoginController {

	@Autowired
	private GoogleConnection googleConnection;

	@Autowired
	private UserService userService;

	Response myResponse=new Response();

	//Token token = new Token();
	@RequestMapping(value="/logInWithGoogle")
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String unid=UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		String googleLogInURL=googleConnection.getGoogleURL(unid);
		System.out.println("googleLoginURL  " + googleLogInURL);
		response.sendRedirect(googleLogInURL);
		return;
	}

	@RequestMapping(value="/connectGoogle")
	public void redirectFromGoogle(HttpServletRequest request,HttpSession session, HttpServletResponse response) throws IOException {

		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");

		if (sessionState == null || !sessionState.equals(googlestate)) {

			response.sendRedirect("loginWithGoogle");

		}

		System.out.println("inside connectGoogle");

		String error = request.getParameter("error");

		if (error != null && error.trim().isEmpty()) {

			response.sendRedirect("login");
		}

		ObjectMapper om = new ObjectMapper();

		String authCode=request.getParameter("code");

		String googleaccessToken = googleConnection.getAccessToken(authCode);
		System.out.println("accessToken " + googleaccessToken);

		JsonNode profile = googleConnection.getUserProfile(googleaccessToken);

		System.out.println("google profile :"+profile);
		System.out.println("google profile is :" + profile.get("name"));

		User user = userService.getUserByEmail(profile.get("email").asText());
		String token = null;

		if (user == null) {
			
			user = new User();
			user.setUserFirstName(profile.get("name").asText());
			user.setEmail(profile.get("email").asText());
			user.setActivated(true);
			int id = userService.createUser(user);
			token = Token.generateToken(id);
			session.setAttribute("token", token);
			System.out.println("Login with FB done!!");
			response.sendRedirect("http://localhost:8080/ToDoProject/#!/dummy");
			
		} else {

			System.out.println("User is Already Exits in DataBase....");
			String url = om.readTree(profile.toString()).get("picture").asText();
			user.setProfileUrl(url);
			
			token = Token.generateToken(user.getUserId());
			session.setAttribute("token", token);
			userService.updateUser(user);
			response.sendRedirect("http://localhost:8080/ToDoProject/#!/dummy");
		}
	}
}

