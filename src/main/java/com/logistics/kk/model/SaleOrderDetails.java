package com.logistics.kk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sale_order_details")
public class SaleOrderDetails {

	@Id
	@GeneratedValue(generator = "sale_order_dtl_gen")
	@SequenceGenerator(name = "sale_order_dtl_gen", sequenceName = "sale_order_dtl_seq")
	@Column(name = "sale_ordr_dtl_id")
	private Integer id;

	@Column(name = "sale_order_dtl_qty")
	private Integer qty;

	@ManyToOne
	@JoinColumn(name = "part_id_fk")
	private Part part;

	@ManyToOne
	@JoinColumn(name = "sale_order_id_fk")
	private SaleOrder saleOrder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public SaleOrderDetails(Integer id, Integer qty, Part part, SaleOrder saleOrder) {
		super();
		this.id = id;
		this.qty = qty;
		this.part = part;
		this.saleOrder = saleOrder;
	}

	public SaleOrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
