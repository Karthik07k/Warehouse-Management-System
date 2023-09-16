package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "saleorder_tab")
public class SaleOrder {

	@Id
	@GeneratedValue(generator = "sale_ord_gen")
	@SequenceGenerator(name = "sale_ord_gen", sequenceName = "sale_ord_seq")
	@Column(name = "sale_ord_id")
	private Integer id;

	@Column(name = "sale_ord_code")
	private String orderCode;

	@Column(name = "sale_ord_refno")
	private String refNum;

	@Column(name = "sale_ord_stockmode")
	private String stockMode;

	@Column(name = "sale_ord_stocksource")
	private String stockSource;

	@Column(name = "sale_ord_status")
	private String status;

	@Column(name = "sale_ord_desc")
	private String description;

	// Integration
	@ManyToOne
	@JoinColumn(name = "st_id_fk")
	private ShipmentType st;

	@ManyToOne
	@JoinColumn(name = "wh_id_fk")
	private WhUserType customer;
}
