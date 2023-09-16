package com.logistics.kk.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.kk.model.Role;
import com.logistics.kk.repo.RoleRepository;
import com.logistics.kk.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository repo;

	public Map<Integer, String> getRolesMap() {
		List<Role> roleList = repo.findAll();
		Map<Integer,String> map = new HashedMap<>();
		for(Role role:roleList) {
			map.put(role.getId(), role.getRole().name());
		}
		return map;
	}

}
