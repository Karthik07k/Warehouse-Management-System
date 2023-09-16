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
@Table(name = "sale_order_details")
public class SaleOrderDetails {

	@Id
	@GeneratedValue(generator = "sale_order_dtl_gen")
	@SequenceGenerator(name = "sale_order_dtl_gen", sequenceName = "sale_order_dtl_seq")
	@Column(name = "sale_ordr_dtl_id")
	private Integer id;

	@Column(name = "sale_order_dtl_qty")
	private Integer qty;

	@ManyToOne
	@JoinColumn(name = "part_id_fk")
	private Part part;

	@ManyToOne
	@JoinColumn(name = "sale_order_id_fk")
	private SaleOrder saleOrder;

}
