package com.meli.quasar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends Exception {

	public MessageNotFoundException(String message) {
		super(message);
	}
}
