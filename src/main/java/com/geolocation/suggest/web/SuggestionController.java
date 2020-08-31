package com.geolocation.suggest.web;

import java.util.ArrayList;
import java.util.List;

import com.geolocation.suggest.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geolocation.suggest.exception.SuggestionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.geolocation.suggest.service.SuggestionService;

/**
 * controller for location suggestion
 */
@RestController
public class SuggestionController extends SuggestionExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(SuggestionController.class);

	@Autowired
	private SuggestionService service;

	/**
	 * end point for /suggest API
	 * 
	 * @param query
	 * @param latitude
	 * @param longitude
	 * @return Suggestions
	 */
	@GetMapping("/suggest")
	public Suggestions getLocations(@RequestParam(name = "q", required = true) String query,
			@RequestParam(name = "latitude", required = false) Double latitude,
			@RequestParam(name = "longitude", required = false) Double longitude) {

		logger.debug("Query params:{},{},{}", query, latitude, longitude);
		if (StringUtils.isEmpty(query)) {
			return new Suggestions();
		}
		return new Suggestions(service.getSuggestions(query, latitude, longitude));
	}

}
