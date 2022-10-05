package com.exmpl.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends Exception {

	public CarNotFoundException(String message) {
		super(message);
	}
	
	public CarNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
