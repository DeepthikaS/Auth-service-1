package com.cts.authmicroservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cts.authmicroservice.model.MessageResponse;

import io.jsonwebtoken.ExpiredJwtException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedExceptions(UnauthorizedException ex) {
	
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Unauthorized request. Login again", HttpStatus.UNAUTHORIZED));
	}

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
	
		return ResponseEntity.badRequest().body(new MessageResponse("Required token", HttpStatus.BAD_REQUEST));
	}

	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {

		
		return ResponseEntity.badRequest().body(new MessageResponse("Token has expired", HttpStatus.BAD_REQUEST));
	}
}
