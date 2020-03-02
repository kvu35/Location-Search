package com;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class AuthenticationServices {
	private static FirebaseApp firebaseContext;

	// Call the firebase service to authenticate and encrypt etcy
	public static boolean firebaseAuthenticate(User user) {
		DatabaseReference reference = FirebaseDatabase.getInstance(firebaseContext).
				getReference().child("users").child(user.getUsername());

		// Attach a listener to read the data at our posts reference
		reference.addListenerForSingleValueEvent(new ValueEventListener() {
		  @Override
		  public void onDataChange(DataSnapshot dataSnapshot) {
		    System.out.printf("Found user %s %s", dataSnapshot.getKey(), dataSnapshot.getValue());
		    //send to frontend here
		  }
		
		  @Override
		  public void onCancelled(DatabaseError databaseError) {
		    System.out.println("The read failed: " + databaseError.getCode());
		  }
		});
		return false;
	}
	
	public static void firebaseRegister(User newUser) {
		DatabaseReference reference = FirebaseDatabase.getInstance(firebaseContext).getReference().child("users");
		Map<String, User> userMap = new HashMap<String, User>();
		userMap.put(newUser.getUsername(), newUser);
		reference.setValueAsync(userMap);
	}
	
	public static void setContext(FirebaseApp new_context) {
		firebaseContext = new_context;
	}
}