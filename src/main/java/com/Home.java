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

@WebServlet(
		name = "Home",
		urlPatterns = {"/home"}
		)
public class Home extends HttpServlet {
	private static GeoApiContext api_context = null;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		// grab the API key and create a new context 
		BufferedReader reader = new BufferedReader(new FileReader("../../../../API.key"));
		String API_KEY = reader.readLine();
		reader.close();
		api_context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		
		String lon = request.getParameter("Longitude");
		String lat = request.getParameter("Lattitude");
		String search_radius = request.getParameter("radius");
		
		NearbySearchRequest search_req = PlacesApi.nearbySearchQuery(api_context,
				new LatLng(Double.valueOf(lon), Double.valueOf(lat)));
		search_req.radius(Integer.valueOf(search_radius));
		search_req.setCallback(new PendingResult.Callback<PlacesSearchResponse>() {
			@Override
			public void onResult(PlacesSearchResponse result) {
				PlacesSearchResult[] results = result.results;
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonObject jsonObject = new JsonObject();
				for (PlacesSearchResult r : results) {
					jsonObject.addProperty(r.name, gson.toJson(r));
				}
				try {
					response.getWriter().print(jsonObject.getAsString());
				} catch(IOException e) {
					response.sendError(0);
				}
			}

			@Override
			public void onFailure(Throwable e) {
				try {
					response.sendError(e.hashCode());
					response.getWriter().print(e.getMessage());
				} catch(IOException e) {
					response.sendError(0);
				}
			}
		});
	}
}