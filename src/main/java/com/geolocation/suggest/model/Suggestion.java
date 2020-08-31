package com.geolocation.suggest.model;

/**
 * model for response item
 */
public class Suggestion {

	private String name;
	private String latitude;
	private String longitude;
	private Double score;

	// no-arg constructor
	public Suggestion() {
	}

	// parameterized constructor
	public Suggestion(String location, String latitude, String longitude, Double score) {
		super();
		this.name = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ResponseItem [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", score=" + score
				+ "]";
	}
}
