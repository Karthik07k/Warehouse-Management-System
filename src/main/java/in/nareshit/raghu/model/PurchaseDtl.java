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
@Table(name="po_dtl_tab")
public class PurchaseDtl {

	@Id
	@GeneratedValue(generator = "pdtl_gen")
	@SequenceGenerator(name = "pdtl_gen",sequenceName = "pdtl_seq")
	@Column(name="po_dlt_id_col")
	private Integer id;
	
	@Column(name="po_dlt_qty_col")
	private Integer qty;
	
	@ManyToOne
	@JoinColumn(name="part_id_fk_col")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="po_id_fk_col")
	private PurchaseOrder po;

	public Object getQty() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPart() {
		// TODO Auto-generated method stub
		return null;
	}
}
