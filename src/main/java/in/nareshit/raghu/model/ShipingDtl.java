package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shiping_dtl_tab")
public class ShipingDtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shiping_dtl_id")
	private Integer id;

	@Column(name = "shiping_dtl_code")
	private String partCode;

	@Column(name = "shiping_dtl_cost")
	private Double baseCost;

	@Column(name = "shiping_dtl_qty")
	private Integer qty;

	@Column(name = "shiping_dtl_status")
	private String status;

}
