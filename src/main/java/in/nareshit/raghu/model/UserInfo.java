package in.nareshit.raghu.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import in.nareshit.raghu.consts.UserMode;
import lombok.Data;

@Data
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
	
}
