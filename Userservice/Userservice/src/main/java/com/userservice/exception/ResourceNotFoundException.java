package com.userservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
