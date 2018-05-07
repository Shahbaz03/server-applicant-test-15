package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SearchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5982635359776631272L;
	
	public SearchException(String message) {
		super(message);
	}

}
