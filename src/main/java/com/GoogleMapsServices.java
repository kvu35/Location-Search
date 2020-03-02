package com;

import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

import io.grpc.netty.shaded.io.netty.handler.ssl.PemPrivateKey;

public class GoogleMapsServices {
	private static GeoApiContext context;

	public static PlacesSearchResult[] getLocation(ServiceRequest request) throws Exception {
		// grab the API key and create a new context 
		String lon = request.getCoordinates()[1];
		String lat = request.getCoordinates()[0];
		String search_radius = request.getRadius();
		
		NearbySearchRequest search_req = PlacesApi.nearbySearchQuery(context,
				new LatLng(Double.valueOf(lat), Double.valueOf(lon)));
		search_req.radius(Integer.valueOf(search_radius));

		PlacesSearchResult[] placesSearchResults = search_req.await().results;
		System.out.println(new GsonBuilder().create().toJson(placesSearchResults));	
		return placesSearchResults;

	//	search_req.setCallback(new PendingResult.Callback<PlacesSearchResponse>() {
	//		@Override
	//		public void onResult(PlacesSearchResponse result) {
	//			try {
	//				//PrintWriter out = response.getWriter();
	//				//response.setContentType("application/json");
	//				//response.setCharacterEncoding("UTF-8");
	//				//response.setStatus(HttpServletResponse.SC_OK);
	//				String tempString = new GsonBuilder().create().toJson(result.results);
	//				//out.print(tempString);
	//				//out.flush();
	//				System.out.printf("Sending %s", tempString);
	//				helper();
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//				System.out.println(e.getMessage());
	//			}
	//		}

	//		@Override
	//		public void onFailure(Throwable e) {
	//			System.out.println(e.toString());
	//		}
	//	});
	}
	
	public static void setContext(GeoApiContext new_context) {
		context = new_context;
	}
	
	public static void end() {
		context.shutdown();
		context = null;
	}
}