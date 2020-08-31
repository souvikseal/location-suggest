package com.geolocation.suggest.dataAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.geolocation.suggest.model.Location;

/**
 * implementation class of Location DAO
 */
@Repository
public class LocationDAOImpl implements LocationDAO {

	private static final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);

	private static final String DATA_REPO = "/Cities.csv";

	/**
	 * populate location info
	 * 
	 * @return a list of location objects
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Override
	public List<Location> populateLocations() {
		InputStream inputStream = getClass().getResourceAsStream(DATA_REPO);
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		List<Location> locations = new ArrayList<>();
		try (CSVParser parser = new CSVParser(new BufferedReader(new InputStreamReader(inputStream)), format)){
			parser.forEach(record -> {
				Location loc = new Location();
				loc.setId(Integer.parseInt(record.get(0)));
				loc.setCity(record.get(1));
				loc.setLatitude(Double.parseDouble(record.get(2)));
				loc.setLongitude(Double.parseDouble(record.get(3)));
				loc.setCountry(record.get(4));
				loc.setState(record.get(5));
				locations.add(loc);
			});
		} catch (Exception e) {
			logger.error("unable to parse the location info", e);
		}
		return locations;
	}
}
