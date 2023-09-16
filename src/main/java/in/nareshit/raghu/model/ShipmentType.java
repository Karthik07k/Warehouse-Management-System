package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="shipment_type_tab")
public class ShipmentType {
	@Id
	@GeneratedValue(
			generator = "ship_type_gen"
			)
	@SequenceGenerator(
			name = "ship_type_gen" , 
			sequenceName = "ship_type_seq")
	@Column(
			name="ship_id_col")
	private Integer id;
	
	@Column(
			name="ship_mode_col",
			nullable = false,
			length = 10
			)
	private String shipMode;
	
	@Column(
			name="ship_code_col",
			nullable = false,
			length = 10,
			unique = true
			)
	private String shipCode;
	
	@Column(
			name="ship_enbl_col",
			nullable = false,
			length = 5
			)
	private String enbleShip;
	
	@Column(
			name="ship_grade_col",
			nullable = false,
			length = 3
			)
	private String shipGrade;
	
	@Column(
			name="ship_desc_col",
			nullable = false,
			length = 100
			)
	private String shipDesc;
}
