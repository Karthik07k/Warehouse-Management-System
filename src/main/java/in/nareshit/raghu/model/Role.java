package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import in.nareshit.raghu.consts.RoleType;
import lombok.Data;

@Data
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
	
}
