package com.geolocation.suggest.model;

/**
 * model for location
 */
public class Location {
    
	private Integer id;
	private String city;
	private String state;
	private String country;
	private Double latitude;
	private Double longitude;
	private Double score;
	private Double distance;

	// no-arg constructor
	public Location() {}

	// parameterized constructor
	public Location(Integer id, String city, String state, String country, Double latitude, Double longitude,
			Double score) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.score = score;
	}

	public String getCity() {
		return city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", score=" + score + "]";
	}
}
