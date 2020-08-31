package com.geolocation.suggest.service;

import java.util.List;

import com.geolocation.suggest.model.Suggestion;

/**
 * interface to list the location suggestion service
 */
public interface SuggestionService {

	/**
	 * retrieve eligible locations for given search parameters
	 * 
	 * @param key:       query city name
	 * @param latitude:  input latitude
	 * @param longitude: input longitude
	 * @return a list of location objects
	 */
	List<Suggestion> getSuggestions(String key, Double latitude, Double longitude);
}
