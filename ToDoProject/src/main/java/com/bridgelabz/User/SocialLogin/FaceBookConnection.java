package com.bridgelabz.User.SocialLogin;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FaceBookConnection {

	private static final String faceBookClientId="1347872695339978";
	private static final String faceBookSecretId="1f1b14eca45bf9dfb71dc3408ea8cd8b";
	public static final String Redirect_URI = "http://localhost:8080/ToDoProject/connectFaceBook";

	public String getFaceBookURL(String unid) {
		
		String facebookLoginURL = "";

		try {
			facebookLoginURL = "http://www.facebook.com/dialog/oauth?" + "client_id=" + faceBookClientId + "&redirect_uri="
					+ URLEncoder.encode(Redirect_URI, "UTF-8") + "&state=" + unid + "&response_type=code"
					+ "&scope=public_profile,email";

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return facebookLoginURL;
	}

	public String getAccessToken(String authCode) throws UnsupportedEncodingException {

		String fbaccessTokenURL = "https://graph.facebook.com/v2.9/oauth/access_token?" + "client_id=" + faceBookClientId
				+ "&redirect_uri=" + URLEncoder.encode(Redirect_URI, "UTF-8") + "&client_secret=" + faceBookSecretId + "&code="
				+ authCode;

		ResteasyClient restCall = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = restCall.target(fbaccessTokenURL);

		Form form = new Form();
		form.param("client_id", faceBookClientId);
		form.param("client_secret", faceBookSecretId);
		form.param("redirect_uri", Redirect_URI);
		form.param("code", authCode);
		form.param("grant_type", "authorization_code");


		Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.form(form));
		String facebookAccessToken = response.readEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		String acc_token = null;

		try {
			acc_token = mapper.readTree(facebookAccessToken).get("access_token").asText();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return acc_token;
	}
	
	public JsonNode getUserProfile(String fbaccessToken) {

		String fbgetUserURL = "https://graph.facebook.com/v2.9/me?access_token=" + fbaccessToken
				+ "&fields=id,name,email,first_name,last_name,picture";

		ResteasyClient restCall = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = restCall.target(fbgetUserURL);

		String headerAuth = "Bearer " + fbaccessToken;
		Response response = target.request()
				.header("Authorization", headerAuth)
				.accept(MediaType.APPLICATION_JSON)
				.get();

		String profile =  response.readEntity(String.class);
		ObjectMapper mapper=new ObjectMapper();
		JsonNode FBprofile = null;

		try {
			FBprofile = mapper.readTree(profile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		restCall.close();
		return FBprofile;
	}
}
