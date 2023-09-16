package com.logistics.kk.service;

import java.util.List;
import java.util.Map;

import com.logistics.kk.model.Part;

public interface IPartService {

	Integer savePart(Part part);
	void updatePart(Part part);
	void deletePart(Integer id);
	
	Part getOnePart(Integer id);
	List<Part> getAllParts();
	
	Map<Integer,String> getPartIdAndCode();
	
}
