package com;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/login")
public class Login {
	@RequestMapping(value="/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String authenticate(@RequestBody User tempUser) {
		System.out.println(tempUser.getPassword() + tempUser.getUsername());
		UserManager manager = new UserManager();
		boolean isUser = manager.checkUser(tempUser);
		JsonObject validUser = new JsonObject();
		validUser.addProperty("valid", isUser);
		String jsonString = "";
		try {
			jsonString = new ObjectMapper().writeValueAsString(validUser);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return jsonString;
		// Call the fire base service to authenticate and encrypt etc
//		try {
//			AuthenticationServices.firebaseAuthenticate(tempUser);
//		} catch (Exception e) { // any other cases
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
	}
}