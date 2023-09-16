package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.consts.RoleType;
import in.nareshit.raghu.model.Role;

public interface RoleRepository 
	extends JpaRepository<Role, Integer> 
{
	//SQL: (select count(role) from roletab where role=?)>0?true:false
	Boolean existsByRole(RoleType role);
}
