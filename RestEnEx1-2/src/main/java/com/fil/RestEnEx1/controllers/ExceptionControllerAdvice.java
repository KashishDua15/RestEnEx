package com.fil.RestEnEx1.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fil.RestEnEx1.services.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> ExceptionHandler(ValidationException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
