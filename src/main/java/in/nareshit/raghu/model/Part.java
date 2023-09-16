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
@Table(name="part_tab")
public class Part {

	@Id
	@GeneratedValue(generator = "part_gen")
	@SequenceGenerator(name = "part_gen" , sequenceName = "part_seq")
	@Column(name="part_id_col")
	private Integer id;
	
	@Column(name="part_code_col")
	private String partCode;

	@Column(name="part_curr_col")
	private String partCurrency;

	@Column(name="part_cost_col")
	private Double partBaseCost;
	
	@Column(name="part_wid_col")
	private Double partWid;
	
	@Column(name="part_ht_col")
	private Double partHt;
	
	@Column(name="part_len_col")
	private Double partLen;
	
	@Column(name="part_desc_col")
	private String partDesc;
	
	//integrations
	@ManyToOne
	@JoinColumn(name="uom_id_fk_col")
	private Uom uom;//HAS-A
	
	@ManyToOne
	@JoinColumn(name="om_id_fk_col")
	private OrderMethod om;//HAS-A
	
	
}
