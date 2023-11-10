package com.userservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.exception.EmptyFieldsException;
import com.userservice.exception.NoDataFoundException;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		String message = resourceNotFoundException.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).success(false)
				.build();
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
		String message = illegalArgumentException.getMessage();
		ApiResponse apiResponse = ApiResponse.builder().message(message).status(HttpStatus.BAD_REQUEST).success(false)
				.build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyFieldsException.class)
	public ResponseEntity<ApiResponse> handleEmptyFieldsException(EmptyFieldsException emptyFieldsException){
		String message = emptyFieldsException.getMessage();
		ApiResponse apiResponse = ApiResponse.builder().message(message).status(HttpStatus.BAD_REQUEST).success(false)
				.build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ApiResponse> handleNoDataFoundException(NoDataFoundException noDataFoundException){
		String message = noDataFoundException.getMessage();
		ApiResponse apiResponse = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).success(false)
			.build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
}
