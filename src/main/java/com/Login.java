package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import okhttp3.Request;

@WebServlet(
		name = "Login",
		urlPatterns = {"/login"}
		)
public class Login extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username == "Username" && password == "Password") {
			response.getWriter().print(true);
			// TODO forward everything
			RequestDispatcher view = request.getRequestDispatcher("../../Webapp/home.html");
			try {
				view.forward(request, response);
			} catch(Throwable e) {
				
			}
		} else {
			response.sendError(1); // username and password mismatch
		}
	}
}