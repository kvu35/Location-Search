package com;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.firebase.FirebaseOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PendingResult;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

public class GoogleMapsServices {
	private static GeoApiContext context;

	public static void getLocation(ServiceRequest request, HttpServletResponse response) throws Exception {
		// grab the API key and create a new context 
		String lon = request.getCoordinates()[1];
		String lat = request.getCoordinates()[0];
		String search_radius = request.getRadius();
		
		NearbySearchRequest search_req = PlacesApi.nearbySearchQuery(context,
				new LatLng(Double.valueOf(lat), Double.valueOf(lon)));
		search_req.radius(Integer.valueOf(search_radius));
		search_req.setCallback(new PendingResult.Callback<PlacesSearchResponse>() {
			@Override
			public void onResult(PlacesSearchResponse result) {
				try {
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.setStatus(HttpServletResponse.SC_OK);
					out.print(new GsonBuilder().create().toJson(result.results));
					out.flush();
					System.out.println("Sending the response");
				} catch (IOException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}

			@Override
			public void onFailure(Throwable e) {
				System.out.println(e.toString());
			}
		});
	}
	
	public static void setContext(GeoApiContext new_context) {
		context = new_context;
	}
	
	public static void end() {
		context.shutdown();
		context = null;
	}
}