package com.geolocation.suggest.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.geolocation.suggest.model.Suggestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

@SpringBootTest
class SuggestServiceTest {

	@Autowired
	SuggestionService service;

	@Test
	void getLocationsUnhappyTest() {
		List<Suggestion> locations = service.getSuggestions("NONAMETEST", null, null);
		assertTrue(CollectionUtils.isEmpty(locations));
	}
	
	@Test
	void getLocationsHappyTest() {
		List<Suggestion> locations = service.getSuggestions("Londo", 43.0, -79.0);
		assertTrue(!CollectionUtils.isEmpty(locations));
	}
}
