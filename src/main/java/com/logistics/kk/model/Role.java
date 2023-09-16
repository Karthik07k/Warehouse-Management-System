package com.logistics.kk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logistics.kk.consts.RoleType;

import lombok.Data;

@Entity
@Table(name="roles_tab")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id_col")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role_name_col")
	private RoleType role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Role(Integer id, RoleType role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
