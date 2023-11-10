package com.ratingservice.exceptions;

public class EmptyFieldsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyFieldsException() {
		// TODO Auto-generated constructor stub
	}
	
	public EmptyFieldsException(String message) {
		super(message);
	}
	
}
