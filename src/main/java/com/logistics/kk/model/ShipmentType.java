package com.logistics.kk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="shipment_type_tab")
public class ShipmentType {
	@Id
	@GeneratedValue(
			generator = "ship_type_gen"
			)
	@SequenceGenerator(
			name = "ship_type_gen" , 
			sequenceName = "ship_type_seq")
	@Column(
			name="ship_id_col")
	private Integer id;
	
	@Column(
			name="ship_mode_col",
			nullable = false,
			length = 10
			)
	private String shipMode;
	
	@Column(
			name="ship_code_col",
			nullable = false,
			length = 10,
			unique = true
			)
	private String shipCode;
	
	@Column(
			name="ship_enbl_col",
			nullable = false,
			length = 5
			)
	private String enbleShip;
	
	@Column(
			name="ship_grade_col",
			nullable = false,
			length = 3
			)
	private String shipGrade;
	
	@Column(
			name="ship_desc_col",
			nullable = false,
			length = 100
			)
	private String shipDesc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShipMode() {
		return shipMode;
	}

	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public String getEnbleShip() {
		return enbleShip;
	}

	public void setEnbleShip(String enbleShip) {
		this.enbleShip = enbleShip;
	}

	public String getShipGrade() {
		return shipGrade;
	}

	public void setShipGrade(String shipGrade) {
		this.shipGrade = shipGrade;
	}

	public String getShipDesc() {
		return shipDesc;
	}

	public void setShipDesc(String shipDesc) {
		this.shipDesc = shipDesc;
	}

	public ShipmentType(Integer id, String shipMode, String shipCode, String enbleShip, String shipGrade,
			String shipDesc) {
		super();
		this.id = id;
		this.shipMode = shipMode;
		this.shipCode = shipCode;
		this.enbleShip = enbleShip;
		this.shipGrade = shipGrade;
		this.shipDesc = shipDesc;
	}

	public ShipmentType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
