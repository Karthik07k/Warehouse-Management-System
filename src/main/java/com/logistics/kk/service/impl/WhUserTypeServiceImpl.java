package com.logistics.kk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.kk.exception.WhUserTypeNotFound;
import com.logistics.kk.model.WhUserType;
import com.logistics.kk.repo.WhUserTypeRepository;
import com.logistics.kk.service.IWhUserTypeService;
import com.logistics.kk.util.MyAppUtil;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService{

	@Autowired
	private WhUserTypeRepository repo;  // HAS -A
	
	public Integer saveWhUserType(WhUserType whut) {
		return repo.save(whut).getId();
	}

	public List<WhUserType> getAllWhUserTypes() {
		return repo.findAll();
	}

	public void deleteWhUserType(Integer id) {
		repo.delete(getOneWhUserType(id));
	}

	@Override
	public WhUserType getOneWhUserType(Integer id) {
		
		return repo.findById(id).orElseThrow(
				()-> new WhUserTypeNotFound("WhUserType '"+id+"'  Not Exit")
		
				);
	}

	@Override
	public void updateWhUserType(WhUserType whut) {
		if(whut.getId()==null || !repo.existsById(whut.getId()))
			throw new  WhUserTypeNotFound("WhUserType   '"+whut.getId()+"' Not Exist");
		repo.save(whut).getId();
		
	}
 
	
	// usercodeExit or not...................................
	@Override
	public boolean isWhUserTypeCodeExit(String code) {
		
		return repo.getWhUserTypeuserCodeCount(code)>0;
	}

	@Override
	public boolean isWhUserTypeCodeExitForEdit(String code, Integer id) {
		
		return repo.getWhUserTypeuserCodeCountForEdit(code, id)>0;
	}

	// userEmailExit or not...................................
	@Override
	public boolean getWhUserTypeuserEmailCount(String email) {
		
		return repo.getWhUserTypeuserEmailCount(email)>0;
	}

	@Override
	public boolean getWhUserTypeuserEmailCountForEdit(String email, Integer id) {
		
		return repo.getWhUserTypeuserEmailCountForEdit(email, id)>0;
	}

	@Override
	public boolean getWhUserTypeuserIdNumCount(String idnum) {
		
		return repo.getWhUserTypeuserIdNumCount(idnum)>0;
	}

	@Override
	public boolean getWhUserTypeuserIdNumCountForEdit(String idnum, Integer id) {
		return repo.getWhUserTypeuserIdNumCountForEdit(idnum, id)>0;
	}

	@Override
	public List<Object[]> getWhUserTypUserIDAndCount() {
		
		return repo.getWhUserTypUserIDAndCount();
	}
	
	public Map<Integer, String> getWhUserIdAndCodeByType(String type) {
		List<Object[]> list = repo.getWhUserIdAndCodeByType(type);
		return MyAppUtil.convertListToMap(list);
	}

}
