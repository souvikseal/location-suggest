package com.geolocation.suggest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.geolocation.suggest.dataAccess.LocationDAO;
import com.geolocation.suggest.model.Province;
import com.geolocation.suggest.model.Suggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.geolocation.suggest.model.Location;

/**
 * class to implement the location suggestion service interface
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {

	private static final Logger logger = LoggerFactory.getLogger(SuggestionServiceImpl.class);

	private static final double HALF_MAX_SCORE = 0.5;

	@Autowired
	private LocationDAO dao;

	@Value("${eligible.distance.max:200}")
	private double maxEligibleDist;

	private List<Location> locations;

	@PostConstruct
	public void initialize() {
		locations = dao.populateLocations();
	}

	/**
	 * build location info
	 */
	@Override
	public List<Suggestion> getSuggestions(String key, Double latitude, Double longitude) {
		List<Suggestion> suggestions = locations.stream()
				.filter(p -> p.getCity().toLowerCase().startsWith(key.toLowerCase()))
				.map(m -> mapToSuggestion(m, key, latitude, longitude)).collect(Collectors.toList());
		Collections.sort(suggestions, Comparator.comparing(Suggestion::getScore).reversed());
		return suggestions;
	}

	private Suggestion mapToSuggestion(Location loc, String key, Double latitude, Double longitude) {
		return new Suggestion(displayFormattedLocation(loc), Double.toString(loc.getLatitude()),
				Double.toString(loc.getLongitude()), getScore(loc, key, latitude, longitude));

	}

	/**
	 * calculate score
	 * 
	 * @param fl:        filtered location
	 * @param key:       searched query key
	 * @param latitude:  input latitude
	 * @param longitude: input longitude
	 * @return a double score
	 */
	private Double getScore(Location fl, String key, Double latitude, Double longitude) {
		Double queryMatchScore = getQueryMatchScore(fl.getCity(), key);
		Double distance = distance(fl.getLatitude(), fl.getLongitude(), latitude, longitude);
		Double distanceMatchScore = distance == null ? queryMatchScore : getDistanceMatchScore(distance);
		logger.info("City : {}, Distance: {}, QueryMatchScore : {}, DistanceMatchScore: {}", fl.getCity(), distance,
				queryMatchScore, distanceMatchScore);
		return roundToFirstDecimal(queryMatchScore + distanceMatchScore);
	}

	/**
	 * round score to first decimal point
	 * 
	 * @param value
	 * @return rounded score
	 */
	private static double roundToFirstDecimal(double value) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(1, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * calculate distance matching score
	 * 
	 * @param distance
	 * @return score
	 */
	private double getDistanceMatchScore(double distance) {
		if (distance > maxEligibleDist)
			return 0.0;
		return (1 - distance / maxEligibleDist) * (HALF_MAX_SCORE);
	}

	/**
	 * calculate query matching score
	 * 
	 * @param city
	 * @param query
	 * @return score
	 */
	private double getQueryMatchScore(String city, String query) {
		// city search score: proportioned to the length of the query
		return HALF_MAX_SCORE * ((double) query.length() / city.length());
	}

	/**
	 * calculate physical distance between 2 locations
	 * 
	 * @param lat1: latitude of the filtered location
	 * @param lon1: longitude of the filtered location
	 * @param lat2: input latitude
	 * @param lon2: input longitude
	 * @return a double distance
	 */
	private Double distance(Double lat1, Double lon1, Double lat2, Double lon2) {
		if ((lat1 == null || lat2 == null || lon1 == null || lon2 == null)) {
			return null;
		}
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0.0;
		}
		double lonDiff = lon1 - lon2;
		double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lonDiff));
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist = dist * 60 * 1.1515;// distance calculated in miles

		return (dist);
	}

	/**
	 * format the location display
	 *
	 * @param loc
	 * @return formatted location
	 */
	private String displayFormattedLocation(Location loc) {
		String state = loc.getState();
		if ("CA".equalsIgnoreCase(loc.getCountry())) {
			state = Province.getNameByCode(loc.getState());
		}
		return loc.getCity() + ", " + state + ", " + loc.getCountry();
	}
}