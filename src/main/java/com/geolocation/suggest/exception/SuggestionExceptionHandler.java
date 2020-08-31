package com.geolocation.suggest.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * class to handle exceptions
 */
public class SuggestionExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * handle missing request params
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(
				displayError("EXCEPTION: Bad request, parameter " + ex.getParameterName() + " is required."), status);
	}

	/**
	 * handle incorrect method arg
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>(displayError("EXCEPTION: Bad request, Invalid argument."), status);
	}

	/**
	 * handle incorrect param types
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>(displayError("EXCEPTION: Bad request, type Mismatch for latitude/longitude."),
				status);
	}

	/**
	 * format error message
	 * 
	 * @param string
	 * @return error
	 */
	private String displayError(String msg) {
		return "<!doctype html><html lang=\"en\"><head><title>HTTP Status 400 – Bad Request</title><style type=\"text/css\">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>"
				+ msg + "</h1></body></html>";
	}
}