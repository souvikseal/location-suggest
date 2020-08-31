package com.geolocation.suggest.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.geolocation.suggest.model.Suggestions;

@SpringBootTest
class SuggestServiceTest {

	@Autowired
	SuggestionController controller;

	@Test
	void getLocationsUnhappyTest() {
		Suggestions suggestions = controller.getLocations("NONAMETEST", null, null);
		assertTrue(CollectionUtils.isEmpty(suggestions.getSuggestions()));
	}

	@Test
	void getLocationsHappyTest() {
		Suggestions suggestions = controller.getLocations("Londo", 43.0, -79.0);
		assertTrue(!CollectionUtils.isEmpty(suggestions.getSuggestions()));
	}
}
