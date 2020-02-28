package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import okhttp3.Request;

@RestController
@RequestMapping("/login")
public class Login {
	@RequestMapping(value="/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void authenticate(@RequestBody User tempUser, HttpServletResponse response) 
			throws IOException {
		// Call the firebase service to authenticate and encrypt etc.
		if(tempUser.getUsername().equals("Username") && tempUser.getPassword().equals("Password")) {
			try {
				response.getWriter().print(true);
			} catch(Throwable e) {
				System.out.println(e.toString());
			}
		} else {
			response.sendError(1); // username and password mismatch
		}
	}
}