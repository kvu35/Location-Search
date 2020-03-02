package com;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.geojson.Point;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.PlacesSearchResult;

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

	@RequestMapping(value="/{SessionID}/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String enumerateLocations(@PathVariable String SessionID, @RequestBody ServiceRequest request) {
		System.out.printf("Session %s request for %s %s\n", SessionID, request.getCoordinates()[0], request.getCoordinates()[1]);
		// check if valid session
		try {
			PlacesSearchResult[] results = GoogleMapsServices.getLocation(request);
			org.geojson.FeatureCollection featureCollection = new org.geojson.FeatureCollection();
			org.geojson.Feature tempFeature = null;
			for (PlacesSearchResult result : results) {
				tempFeature = new org.geojson.Feature();
				tempFeature.setGeometry(new Point(result.geometry.location.lat, result.geometry.location.lng));
				tempFeature.setProperty("name", result.name);
				featureCollection.add(tempFeature);
			}
			String jsonString = new ObjectMapper().writeValueAsString(featureCollection);
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return "";
	}
}