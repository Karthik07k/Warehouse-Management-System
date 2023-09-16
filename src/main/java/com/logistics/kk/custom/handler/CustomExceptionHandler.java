package com.logistics.kk.custom.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.logistics.kk.custom.error.ErrorType;
import com.logistics.kk.exception.ShipmentTypeNotFoundException;
import com.logistics.kk.exception.UomNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(UomNotFoundException.class)
	public ResponseEntity<ErrorType> handleUomNotFound(
			UomNotFoundException unfe
			)
	{
		return new ResponseEntity<ErrorType>(
				new ErrorType(
						new Date().toString(), 
						"UOM", 
						unfe.getMessage()
						),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ShipmentTypeNotFoundException.class)
	public ResponseEntity<ErrorType> handleShipmentTypeNotFound(
			ShipmentTypeNotFoundException unfe
			)
	{
		return new ResponseEntity<ErrorType>(
				new ErrorType(
						new Date().toString(), 
						"SHIPMENT TYPE", 
						unfe.getMessage()
						),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
