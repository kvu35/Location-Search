package com;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.google.appengine.repackaged.com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/User")
public class User {
	private String username;
	private String password;
	private String UserID;

	public User() {
		username = null;
		password = null;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@RequestMapping(value="/{SessionID}/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void enumerateLocations(@PathVariable String SessionID, @RequestBody ServiceRequest request, HttpServletResponse response) {
		System.out.printf("Session %s request for %s %s\n", SessionID, request.getCoordinates()[0], request.getCoordinates()[1]);
		// check if valid session
		try {
			GoogleMapsServices.getLocation(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}