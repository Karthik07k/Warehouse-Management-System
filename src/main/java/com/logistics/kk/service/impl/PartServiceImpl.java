package com.logistics.kk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.kk.exception.PartNotFoundException;
import com.logistics.kk.model.Part;
import com.logistics.kk.repo.PartRepository;
import com.logistics.kk.service.IPartService;
import com.logistics.kk.util.MyAppUtil;

@Service
public class PartServiceImpl implements IPartService {
	
	@Autowired
	private PartRepository repo;

	public Integer savePart(Part part) {
		return repo.save(part).getId();
	}

	public void updatePart(Part part) {
		repo.save(part);
	}

	public void deletePart(Integer id) {
		repo.delete(getOnePart(id));
	}

	
	public Part getOnePart(Integer id) {
		return repo.findById(id).orElseThrow(
				()->new PartNotFoundException("not exist")
				);
	}

	
	public List<Part> getAllParts() {
		return repo.findAll();
	}
	
	public Map<Integer, String> getPartIdAndCode() {
		List<Object[]> list = repo.getPartIdAndCode();
		return MyAppUtil.convertListToMap(list);
	}

}
