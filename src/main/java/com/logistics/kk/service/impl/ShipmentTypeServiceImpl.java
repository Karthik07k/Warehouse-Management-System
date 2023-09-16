package com.logistics.kk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.kk.exception.ShipmentTypeNotFoundException;
import com.logistics.kk.model.ShipmentType;
import com.logistics.kk.repo.ShipmentTypeRepository;
import com.logistics.kk.service.IShipmentTypeService;
import com.logistics.kk.util.MyAppUtil;

@Service
public class ShipmentTypeServiceImpl 
implements IShipmentTypeService
{

	@Autowired
	private ShipmentTypeRepository repo; //HAS-A

	public Integer saveShipmentType(ShipmentType st) {
		st = repo.save(st);
		return st.getId(); //return PK(ID)
	}

	public List<ShipmentType> getAllShipmentTypes() {
		List<ShipmentType> list = repo.findAll();
		return list;
	}

	/*
	public void deleteShipmentType(Integer id) {
		//is given object present
		//then do delete
		//else throw exception
		repo.deleteById(id);
	}

	public ShipmentType getShipmentType(Integer id) {
		Optional<ShipmentType> opt = repo.findById(id);
		if(opt.isEmpty()) {
			throw new ShipmentTypeNotFoundException("ShipmentType '"+id+"' Not Exist");
		} else {
			return opt.get();
		}
	}*/
	
	public void deleteShipmentType(Integer id) {
		repo.delete(getShipmentType(id));
	}

	public ShipmentType getShipmentType(Integer id) {
		return repo.findById(id)
				.orElseThrow(
						()->new ShipmentTypeNotFoundException(
								"ShipmentType '"+id+"' Not Exist")
						);
	}

	public void updateShipmentType(ShipmentType st) {
		//is given object present
		//then update
		//else throw exception
		repo.save(st);
	}

	public boolean isShipmentTypeCodeExist(String code) {
		/*
		Integer count = repo.getShipmentTypeCodeCount(code);
		boolean isExist = count > 0 ? true: false;
		return isExist;*/
		//return repo.getShipmentTypeCodeCount(code) > 0 ? true: false;
		return repo.getShipmentTypeCodeCount(code) > 0 ;
	}

	public boolean isShipmentTypeCodeExistForEdit(String code,Integer id) {
		return repo.getShipmentTypeCodeCountForEdit(code, id) > 0;
	}
	
	public List<Object[]> getShipmentTypeModeAndCount() {
		return repo.getShipmentTypeModeAndCount();
	}
	
	public Map<Integer, String> getShipmentIdAndCodeByEnable(String enable) {
		List<Object[]> list =  repo.getShipmentIdAndCodeByEnable(enable);
		return MyAppUtil.convertListToMap(list);
	}
}
