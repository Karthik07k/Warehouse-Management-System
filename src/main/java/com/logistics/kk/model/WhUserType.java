package com.logistics.kk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
 
@Entity
@Table(name="wh_user_type_tab")
public class WhUserType {
	
	@Id
	@Column(name="wh_usr_id_col")
	@GeneratedValue(generator = "whusr_gen")
	@SequenceGenerator(name = "whusr_gen", sequenceName = "whgen_seq")
	private Integer id;
	
	@Column(name="wh_usr_type_col")
	private String userType;
	
	@Column(name="wh_usr_code_col")
	private String userCode;
	
	@Column(name="wh_usr_for_col")
	private String userFor;
	
	@Column(name="wh_usr_email_col")
	private String userEmail;
	
	@Column(name="wh_usr_contact_col")
	private String userContact;
	
	@Column(name="wh_usr_id_type_col")
	private String userIdType;
	
	@Column(name="wh_usr_other_col")
	private String ifOther;
	
	@Column(name="wh_usr_id_num_col")
	private String userIdNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserFor() {
		return userFor;
	}

	public void setUserFor(String userFor) {
		this.userFor = userFor;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserIdType() {
		return userIdType;
	}

	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}

	public String getIfOther() {
		return ifOther;
	}

	public void setIfOther(String ifOther) {
		this.ifOther = ifOther;
	}

	public String getUserIdNum() {
		return userIdNum;
	}

	public void setUserIdNum(String userIdNum) {
		this.userIdNum = userIdNum;
	}

	public WhUserType(Integer id, String userType, String userCode, String userFor, String userEmail,
			String userContact, String userIdType, String ifOther, String userIdNum) {
		super();
		this.id = id;
		this.userType = userType;
		this.userCode = userCode;
		this.userFor = userFor;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.userIdType = userIdType;
		this.ifOther = ifOther;
		this.userIdNum = userIdNum;
	}

	public WhUserType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
