package com.logistics.kk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logistics.kk.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {

	// register check
	@Query("SELECT count(userCode) from WhUserType where userCode=:code")
	Integer getWhUserTypeuserCodeCount(String code);

	@Query("SELECT count(userCode) from WhUserType where userCode=:code and id!=:id")
	Integer getWhUserTypeuserCodeCountForEdit(String code, Integer id);

	@Query("SELECT count(userEmail) from WhUserType where userEmail=:email")
	Integer getWhUserTypeuserEmailCount(String email);

	@Query("SELECT count(userCode) from WhUserType where userCode=:email and id!=:id")
	Integer getWhUserTypeuserEmailCountForEdit(String email, Integer id);

	@Query("SELECT count(userIdNum) from WhUserType where userIdNum=:idnum")
	Integer getWhUserTypeuserIdNumCount(String idnum);

	@Query("SELECT count(userIdNum) from WhUserType where userIdNum=:idnum and id!=:id")
	Integer getWhUserTypeuserIdNumCountForEdit(String idnum, Integer id);

	//For Charts Data
	@Query("SELECT userIdType, count(userIdType) FROM WhUserType GROUP BY userIdType")
	List<Object[]> getWhUserTypUserIDAndCount();
	
	//for integration
	@Query("SELECT id, userCode FROM WhUserType WHERE userType=:type")
	List<Object[]> getWhUserIdAndCodeByType(String type);

}
