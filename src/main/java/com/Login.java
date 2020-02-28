package com;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping("/login")
public class Login {
	@RequestMapping(value="/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody User tempUser) {
		System.out.println(tempUser.getPassword() + tempUser.getUsername());
		// Call the firebase service to authenticate and encrypt etcy
		try {
			if(new Service().firebaseAuthenticate(tempUser)) {
				return ResponseEntity.status(HttpStatus.OK).body(Double.toString(new Double(10000)));
			}
		} catch (Exception e) { // any other cases
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user");
	}
}