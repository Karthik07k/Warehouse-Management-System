package com.logistics.kk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shiping_dtl_tab")
public class ShipingDtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shiping_dtl_id")
	private Integer id;

	@Column(name = "shiping_dtl_code")
	private String partCode;

	@Column(name = "shiping_dtl_cost")
	private Double baseCost;

	@Column(name = "shiping_dtl_qty")
	private Integer qty;

	@Column(name = "shiping_dtl_status")
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ShipingDtl(Integer id, String partCode, Double baseCost, Integer qty, String status) {
		super();
		this.id = id;
		this.partCode = partCode;
		this.baseCost = baseCost;
		this.qty = qty;
		this.status = status;
	}

	public ShipingDtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
