package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cart not found")
public class CartNotFoundException extends Exception {
	
	public CartNotFoundException() {
		super();
	}

	public CartNotFoundException(String message) {
		super(message);
	}

}
