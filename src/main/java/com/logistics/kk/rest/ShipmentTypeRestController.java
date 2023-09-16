package com.logistics.kk.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.kk.model.ShipmentType;
import com.logistics.kk.service.IShipmentTypeService;

@RestController
@RequestMapping("/rest/api/st")
public class ShipmentTypeRestController {
	
	@Autowired
	private IShipmentTypeService service;

	/**
	 * 1. Fetch all rows from DB table
	 */
	@GetMapping("/all")
	public ResponseEntity<List<ShipmentType>> getAllShipmentTypes() {
		List<ShipmentType> list = service.getAllShipmentTypes();
		return ResponseEntity.ok(list);
	}

	/**
	 * 2. fetch one row data by id
	 *     from DB table
	 */
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ShipmentType> getOneShipmentType(
			@PathVariable Integer id
			) 
	{
		ShipmentType st = service.getShipmentType(id);
		return ResponseEntity.ok(st);
	}
	
	/***
	 * 3. Read JSON Input , convert to object format
	 *    Store inside Database, return success message
	 * 
	 */
	
	@PostMapping("/create")
	public ResponseEntity<String> createShipmentType(
			@RequestBody ShipmentType shipmentType
			) 
	{
		Integer id = service.saveShipmentType(shipmentType);
		//String message = String.format("Shipment Type '%d' created!!",id);
		String message = "Shipment Type '"+id+"' created!!";
		return new ResponseEntity<>(message, HttpStatus.CREATED);//201
	}
	
	/***
	 * 4. Delete row from DB table using ID(PK)
	 * 
	 */
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteShipmentType(
			@PathVariable Integer id
			) 
	{
		service.deleteShipmentType(id);
		String message = "Shipment Type '"+id+"' Deleted!!";
		return ResponseEntity.ok(message);
	}
	
	/***
	 * 5. Read JSON Input, Convert to Object
	 * 	  check given id exist or not
	 * 	  if exist update data in DB table
	 */
	
	@PutMapping("/modify")
	public ResponseEntity<String> updateShipmentType(
			@RequestBody ShipmentType shipmentType
			)
	{
		service.updateShipmentType(shipmentType);
		String message = "Shipment Type updated!!";
		return ResponseEntity.ok(message);
	}
}
