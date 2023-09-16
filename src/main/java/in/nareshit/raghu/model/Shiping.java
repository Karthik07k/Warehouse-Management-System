package in.nareshit.raghu.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shiping_tab")
public class Shiping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shiping_id")
	private Integer id;

	@Column(name = "shiping_code")
	private String shipingCode;

	@Column(name = "shiping_ref_num")
	private String shipngRefNum;

	@Column(name = "courier_ref_num")
	private String courierRefNum;

	@Column(name = "contact_details")
	private String contactDetails;

	@Column(name = "shiping_desc")
	private String description;

	// 1...1
	@ManyToOne
	@JoinColumn(name = "so_id_fk", unique = true)
	private SaleOrder so;

	// 1...*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "shiping_id_fk")
	private Set<ShipingDtl> dtls;

}
