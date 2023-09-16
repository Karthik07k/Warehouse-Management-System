package com.logistics.kk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.kk.consts.RoleType;
import com.logistics.kk.model.Role;

public interface RoleRepository 
	extends JpaRepository<Role, Integer> 
{
	//SQL: (select count(role) from roletab where role=?)>0?true:false
	Boolean existsByRole(RoleType role);
}
