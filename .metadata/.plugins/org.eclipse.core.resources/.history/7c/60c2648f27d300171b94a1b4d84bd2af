package com.bridgelabz.User.Controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


	@RequestMapping(value="/logInWithGoogle")
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String unid=UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		String googleLogInURL=googleConnection.getGoogleURL(unid);
		response.sendRedirect(googleLogInURL);
		return;
	}

	@RequestMapping(value="/connectGoogle")
	public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");

		if (sessionState == null || !sessionState.equals(googlestate)) {

			response.sendRedirect("loginWithGoogle");

		}

		String error = request.getParameter("error");

		if (error != null && error.trim().isEmpty()) {

			response.sendRedirect("login");
		}
		String authCode=request.getParameter("code");

		String googleaccessToken = googleConnection.getAccessToken(authCode);

		JsonNode profile = googleConnection.getUserProfile(googleaccessToken);
		String fbaccessToken = googleConnection.getAccessToken(authCode);
		JsonNode profileData = googleConnection.getUserProfile(fbaccessToken);
		ObjectMapper om = new ObjectMapper();

		System.out.println(profileData);
		String fbName = profileData.get("name").asText();
		System.out.println(fbName);
		User user = userService.getUserByEmail(profileData.get("email").asText());
		System.out.println(user);
		if (user == null) {

			User fbUser = new User();
			fbUser.setUserFirstName(fbName);
			fbUser.setEmail(profileData.get("email").asText());
			fbUser.setActivated(true);
			String u = om.readTree(profileData.toString()).get("picture").get("data").get("url").asText();
			System.out.println(u);
			fbUser.setProfileUrl(u);
			int id = userService.createUser(fbUser);
			String token = Token.generateToken(id);
			response.setHeader("login", token);
			System.out.println("Login with google is done!!");

		} 
		else {

			System.out.println("User is Already Exits in DataBase....");
		}
		String profileImage = profileData.get("picture").get("data").get("url").asText();
		System.out.println("EMAIL: " + profileData.get("email").asText());
		System.out.println("NAME: " + profileData.get("name").asText());
		System.out.println(profileImage);

	}
}
