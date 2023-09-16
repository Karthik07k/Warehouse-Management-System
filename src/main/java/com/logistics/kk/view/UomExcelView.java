package com.logistics.kk.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.logistics.kk.model.Uom;

public class UomExcelView extends AbstractXlsxView {

	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception 
	{
		//file name
		response.addHeader("Content-Disposition", "attachment;filename=UOMS.xlsx");

		//read data
		@SuppressWarnings("unchecked")
		List<Uom> list = (List<Uom>) model.get("list");

		//create sheet
		Sheet sheet = workbook.createSheet("UOMS");

		//create row#0
		addHead(sheet);
		//create row#1 onwards
		addBody(sheet,list);
	}


	private void addHead(Sheet sheet) {
		Row row  = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("MODEL");
		row.createCell(3).setCellValue("NOTE");
	}

	private void addBody(Sheet sheet, List<Uom> list) {
		int rowNum = 1;
		for(Uom uom : list) {
			Row row  = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(uom.getId());
			row.createCell(1).setCellValue(uom.getUomType());
			row.createCell(2).setCellValue(uom.getUomModel());
			row.createCell(3).setCellValue(uom.getUomDesc());
		}
	}
}
