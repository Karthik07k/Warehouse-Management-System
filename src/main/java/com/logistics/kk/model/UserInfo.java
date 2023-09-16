package com.logistics.kk.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.logistics.kk.consts.UserMode;

import lombok.Data;

@Entity
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String email;
	private String password;
	private String otp;
	
	@Enumerated(EnumType.STRING)
	private UserMode mode = UserMode.DISABLED;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public UserMode getMode() {
		return mode;
	}

	public void setMode(UserMode mode) {
		this.mode = mode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserInfo(Integer id, String name, String email, String password, String otp, UserMode mode,
			Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.mode = mode;
		this.roles = roles;
	}

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
