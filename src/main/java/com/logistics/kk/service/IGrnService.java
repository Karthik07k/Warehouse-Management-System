package com.logistics.kk.service;

import java.util.List;

import com.logistics.kk.model.Grn;

public interface IGrnService {

	Integer saveGrn(Grn grn);
	List<Grn> fetchAllGrns();
	Grn getOneGrn(Integer id);
	
	void updateGrnDtlStatus(Integer id,String status);
}
