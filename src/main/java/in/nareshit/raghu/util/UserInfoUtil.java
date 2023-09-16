package in.nareshit.raghu.util;

import java.util.Set;
import java.util.stream.Collectors;

import in.nareshit.raghu.model.Role;

public interface UserInfoUtil {

	public static Set<String> getRolesAsString(Set<Role> roles) {
		return roles
				.stream()
				//Role(C) -> RoleType(E).name() -->String
				.map(role->role.getRole().name())
				.collect(Collectors.toSet());
	}
}
