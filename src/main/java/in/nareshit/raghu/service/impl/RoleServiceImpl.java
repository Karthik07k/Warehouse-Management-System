package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.model.Role;
import in.nareshit.raghu.repo.RoleRepository;
import in.nareshit.raghu.service.IRoleService;

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
