package com.geolocation.suggest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * model for suggestions
 */
public class Suggestions {
	
	private List<Suggestion> suggestions;

	public Suggestions() {
		suggestions = new ArrayList<>();
	}

	public Suggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> item) {
		this.suggestions = item;
	}

	@Override
	public String toString() {
		return "Suggestions [item=" + suggestions + "]";
	}	
}
