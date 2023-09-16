package com.logistics.kk.service;

import java.util.List;
import java.util.Map;

import com.logistics.kk.model.Uom;

public interface IUomService {

	Integer saveUom(Uom uom);
	void updateUom(Uom uom);
	void deleteUom(Integer id);
	
	Uom getOneUom(Integer id);
	List<Uom> getAllUoms();
	boolean isUomModelExist(String uomModel);
	boolean isUomModelExistForEdit(String uomModel,Integer id);
	
	Map<Integer,String> getUomIdAndModel();
	

}
