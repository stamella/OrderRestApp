package com.capgemini.orderapp.exception;

public class OrderDoesnotExistsException extends RuntimeException {
	
	public OrderDoesnotExistsException(String message) {
		super(message);
	}


}
