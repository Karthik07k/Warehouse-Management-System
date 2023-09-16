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
@Table(name="grn_dtl_tab")
public class GrnDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="grn_dtl_id_col")
	private Integer id;
	
	@Column(name="grn_dtl_code_col")
	private String partCode;
	
	@Column(name="grn_dtl_cost_col")
	private Double baseCost;
	
	@Column(name="grn_dtl_qty_col")
	private Integer qty;
	
	@Column(name="grn_dtl_status_col")
	private String status;

	public void setQty(Object qty2) {
		// TODO Auto-generated method stub
		
	}
}
