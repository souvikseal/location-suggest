package com.geolocation.suggest.dataAccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.geolocation.suggest.model.Location;

/**
 * interface for location DAO
 */
public interface LocationDAO {
	/**
	 * populate location info
	 * 
	 * @return a list of location objects
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	List<Location> populateLocations();
}
