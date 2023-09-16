package com.logistics.kk.service;

import java.util.List;

import com.logistics.kk.model.Document;

public interface IDocumentService {

	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);
}
