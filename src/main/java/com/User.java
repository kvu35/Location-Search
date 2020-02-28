package com;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PendingResult;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.GeolocationResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

@RestController
@RequestMapping("/User")
public class User {
	private Service locationSearch = null;
	private String username;
	private String password;
	private String UserID;
	private boolean authenticated;

	public User() {
		username = null;
		password = null;
		locationSearch = null;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		locationSearch = new Service();
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void startServices() {
		locationSearch = new Service();
	}

	@RequestMapping(value="/{SessionID}/Home", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public static Gson enumerateLocations(@PathVariable String SessionID) {
		return new Gson();
	}
}