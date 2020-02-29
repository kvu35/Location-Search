package com;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PendingResult;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.GeolocationResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.GeocodingApi;

public class Service {
	private static GeoApiContext api_context = null;

	public static void getLocation(ServiceRequest request) throws FileNotFoundException, IOException, Exception {
		// grab the API key and create a new context 
		//BufferedReader reader = new BufferedReader(new FileReader("../../../../API.key"));
		//String API_KEY = reader.readLine();
		//reader.close();
		String API_KEY = "temp";
		api_context = new GeoApiContext.Builder().apiKey(API_KEY).build();

		String lon = request.getCoordinates()[1];
		String lat = request.getCoordinates()[0];
		String search_radius = request.getRadius();
		
		NearbySearchRequest search_req = PlacesApi.nearbySearchQuery(api_context,
				new LatLng(Double.valueOf(lat), Double.valueOf(lon)));
		search_req.radius(Integer.valueOf(search_radius));
		System.out.printf("Sent request to google maps api with %f %f\n", Double.valueOf(lat), Double.valueOf(lon));

		search_req.setCallback(new PendingResult.Callback<PlacesSearchResponse>() {
			@Override
			public void onResult(PlacesSearchResponse result) {
				PlacesSearchResult[] results = result.results;
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonObject jsonObject = new JsonObject();
				for (PlacesSearchResult r : results) {
					System.out.println(r.name);
					jsonObject.addProperty(r.name, gson.toJson(r));
				}
			}

			@Override
			public void onFailure(Throwable e) {
				System.out.println(e.toString());
			}
		});
	}
	
	// Call the firebase service to authenticate and encrypt etcy
	public static boolean firebaseAuthenticate(User user) {
		if(user.getUsername().equals("Username") && user.getPassword().equals("Password")) {
			return true;
		}
		return false;
	}
}