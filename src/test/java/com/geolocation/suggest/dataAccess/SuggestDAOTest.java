package com.geolocation.suggest.dataAccess;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.geolocation.suggest.model.Location;

@SpringBootTest
class SuggestDAOTest {

	@Autowired
	LocationDAO dao;

	@Test
	void populateLocationsTest() throws FileNotFoundException, IOException {
		List<Location> locations = dao.populateLocations();
		assertTrue(locations.size() > 0);
		for (Location loc : locations) {
			assertTrue(loc.getCity() != null && loc.getState() != null && loc.getCountry() != null);
		}
	}
}
