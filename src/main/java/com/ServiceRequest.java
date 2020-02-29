package com;

public class ServiceRequest {
	private String radius;
	private String address;
	private String[] coordinates;
	private static enum ServiceType {
		FIRE_BASE, GOOGLE_MAPS_API
	}

	public String getAddress() {
		return address;
	}
	
	public String[] getCoordinates() {
		return coordinates;
	}

	public String getRadius() {
		return radius;
	}
}