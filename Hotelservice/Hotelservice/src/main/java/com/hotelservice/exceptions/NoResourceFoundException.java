package com.hotelservice.exceptions;

public class NoResourceFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoResourceFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public NoResourceFoundException(String message) {
		super(message);
	}
	
}
