package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.maps.GeoApiContext;

@RestController
@SpringBootApplication
public class LocationSearchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LocationSearchApplication.class, args);
	}
	
	@Component
	private class ServiceListeners implements ApplicationListener<ApplicationReadyEvent> {
		public void onApplicationEvent(ApplicationReadyEvent event) {
			try {
				// Start the database service
				FileInputStream serviceAccount = new FileInputStream("/home/khang/git/Location-Search/keys/firebase_key.json");
				
				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://location-search-266916.firebaseio.com")
				  .build();
				
				AuthenticationServices.setContext(FirebaseApp.initializeApp(options));

				// Start the google maps api service
				BufferedReader reader = new BufferedReader(new FileReader("/home/khang/git/Location-Search/keys/API.key"));
				String API_KEY = reader.readLine();
				reader.close();
				GoogleMapsServices.setContext(new GeoApiContext.Builder().apiKey(API_KEY).build());
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
	}
}