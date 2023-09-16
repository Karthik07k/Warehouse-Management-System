package com.logistics.kk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="grn_dtl_tab")
public class GrnDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="grn_dtl_id_col")
	private Integer id;
	
	@Column(name="grn_dtl_code_col")
	private String partCode;
	
	@Column(name="grn_dtl_cost_col")
	private Double baseCost;
	
	@Column(name="grn_dtl_qty_col")
	private Integer qty;
	
	@Column(name="grn_dtl_status_col")
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

	public GrnDtl(Integer id, String partCode, Double baseCost, Integer qty, String status) {
		super();
		this.id = id;
		this.partCode = partCode;
		this.baseCost = baseCost;
		this.qty = qty;
		this.status = status;
	}

	public GrnDtl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
