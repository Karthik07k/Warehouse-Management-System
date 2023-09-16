package com.logistics.kk.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="grn_tab")
public class Grn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="grn_id_col")
	private Integer id;
	
	@Column(name="grn_code_col")
	private String grnCode;
	
	@Column(name="grn_type_col")
	private String grnType;
	
	@Column(name="grn_desc_col")
	private String grnDescription;
	
	// 1...1
	@ManyToOne
	@JoinColumn(name="po_id_fk_col",unique = true)
	private PurchaseOrder po;
	
	//1...*
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name="grn_id_fk_col")
	private Set<GrnDtl> dtls;

	public Object getPo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrnCode() {
		return grnCode;
	}

	public void setGrnCode(String grnCode) {
		this.grnCode = grnCode;
	}

	public String getGrnType() {
		return grnType;
	}

	public void setGrnType(String grnType) {
		this.grnType = grnType;
	}

	public String getGrnDescription() {
		return grnDescription;
	}

	public void setGrnDescription(String grnDescription) {
		this.grnDescription = grnDescription;
	}

	public Set<GrnDtl> getDtls() {
		return dtls;
	}

	public void setDtls(Set<GrnDtl> dtls) {
		this.dtls = dtls;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	public Grn(Integer id, String grnCode, String grnType, String grnDescription, PurchaseOrder po, Set<GrnDtl> dtls) {
		super();
		this.id = id;
		this.grnCode = grnCode;
		this.grnType = grnType;
		this.grnDescription = grnDescription;
		this.po = po;
		this.dtls = dtls;
	}

	public Grn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
