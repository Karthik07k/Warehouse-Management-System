package in.nareshit.raghu.custom.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nareshit.raghu.custom.error.ErrorType;
import in.nareshit.raghu.exception.ShipmentTypeNotFoundException;
import in.nareshit.raghu.exception.UomNotFoundException;

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
