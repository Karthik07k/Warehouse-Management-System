package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="doc_tab")
public class Document {
	@Id
	@Column(name="doc_id_col")
	private Long docId;

	@Column(name="doc_name_col")
	private String docName;
	
	@Lob
	@Column(name="doc_data_col")
	private byte[] docData;

	public void setDocId(Long docId2) {
		// TODO Auto-generated method stub
		
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public byte[] getDocData() {
		return docData;
	}

	public void setDocData(byte[] docData) {
		this.docData = docData;
	}

	public Long getDocId() {
		return docId;
	}

	public Document(Long docId, String docName, byte[] docData) {
		super();
		this.docId = docId;
		this.docName = docName;
		this.docData = docData;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
