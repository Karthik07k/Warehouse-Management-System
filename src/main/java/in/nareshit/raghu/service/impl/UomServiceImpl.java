package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.UomNotFoundException;
import in.nareshit.raghu.model.Uom;
import in.nareshit.raghu.repo.UomRepository;
import in.nareshit.raghu.service.IUomService;
import in.nareshit.raghu.util.MyAppUtil;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository repo; //HAS-A

	public Integer saveUom(Uom uom) {
		uom = repo.save(uom);
		return uom.getId();
	}

	public void updateUom(Uom uom) {
		if(uom.getId() == null || !repo.existsById(uom.getId())) {
			throw new UomNotFoundException(
					"Uom '"+(uom.getId()==null?"id":uom.getId())+"' not exist for update!");
		} else {
			repo.save(uom);
		}
		
	}

	public void deleteUom(Integer id) {
		repo.delete(getOneUom(id));
	}

	public Uom getOneUom(Integer id) {
		return repo.findById(id)
				.orElseThrow(
						()->new UomNotFoundException(
								"Uom '"+id+"' Not exist")
						);
	}

	public List<Uom> getAllUoms() {
		return repo.findAll();
	}

	public boolean isUomModelExist(String uomModel) {
		return repo.getUomModelCount(uomModel)>0;
	}

	public boolean isUomModelExistForEdit(String uomModel, Integer id) {
		return repo.getUomModelCountForEdit(uomModel, id)>0;
	}

	public Map<Integer, String> getUomIdAndModel() {
		List<Object[]> list =  repo.getUomIdAndModel();
		return MyAppUtil.convertListToMap(list);
		
	}

}
