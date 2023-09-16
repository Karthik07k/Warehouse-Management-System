package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.model.Grn;

public interface IGrnService {

	Integer saveGrn(Grn grn);
	List<Grn> fetchAllGrns();
	Grn getOneGrn(Integer id);
	
	void updateGrnDtlStatus(Integer id,String status);
}
