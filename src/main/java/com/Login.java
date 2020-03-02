package com;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Login {
	@RequestMapping(value="/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void authenticate(@RequestBody User tempUser) {
		System.out.println(tempUser.getPassword() + tempUser.getUsername());
		// Call the fire base service to authenticate and encrypt etc
		try {
			AuthenticationServices.firebaseAuthenticate(tempUser);
		} catch (Exception e) { // any other cases
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}