package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.model.Uom;

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
