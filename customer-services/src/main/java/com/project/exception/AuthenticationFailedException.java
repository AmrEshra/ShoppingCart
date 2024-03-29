package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Authentication failed: Invalid username or password")
public class AuthenticationFailedException extends Exception {

	public AuthenticationFailedException() {
		super();
	}

	public AuthenticationFailedException(String message) {
		super(message);
	}
}
