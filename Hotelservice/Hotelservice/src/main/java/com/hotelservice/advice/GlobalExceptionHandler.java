package com.hotelservice.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelservice.exceptions.EmptyFieldsException;
import com.hotelservice.exceptions.NoDataFoundException;
import com.hotelservice.exceptions.NoResourceFoundException;
import com.hotelservice.payload.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(NoResourceFoundException resourceNotFoundException) {
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
