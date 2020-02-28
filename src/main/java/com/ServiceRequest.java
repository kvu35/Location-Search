package com;

public class ServiceRequest {
	private String radius;
	private String address = null;
	private String[] coordinates = null;
	private static enum ServiceType {
		FIRE_BASE, GOOGLE_MAPS_API
	}

	public ServiceRequest(String radius, String address) {
		this.radius = radius;
		this.address = address;
	}

	public ServiceRequest(String radius, String[] coordinates) {
		this.radius = radius;
		this.coordinates = coordinates;
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