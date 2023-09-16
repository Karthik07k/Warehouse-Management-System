package in.nareshit.raghu.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nareshit.raghu.model.Uom;

public class UomPdfView extends AbstractPdfView {
	
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		HeaderFooter header = new HeaderFooter(new Phrase("UOM PDF BY NIT"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		HeaderFooter footer = new HeaderFooter(new Phrase("UOM PAGE#"), true);
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.setFooter(footer);
		
		
	}

	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception 
	{

		Image img = Image.getInstance("https://s3-ap-southeast-1.amazonaws.com/tv-prod/member/photo/745494-large.jpg");
		//set width and height
		img.scaleAbsolute(250, 60);
		//set alignment
		img.setAlignment(Element.ALIGN_CENTER);
		//add to document
		document.add(img);
		
		// download file with your own name
		response.addHeader("Content-Disposition", "attachment;filename=uoms.pdf");
		
		Font titleFont = new Font(Font.TIMES_ROMAN,24,Font.BOLD,Color.RED);
		Paragraph title = new Paragraph("UOMS DATA PDF",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(20.0f);
		title.setSpacingBefore(15.0f);
		
		
		document.add(title);
		
		Font tableHead = new Font(Font.TIMES_ROMAN,12,Font.BOLD,Color.MAGENTA);
		
		PdfPTable table = new PdfPTable(4);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table.addCell(new Phrase("ID",tableHead));
		table.addCell(new Phrase("TYPE",tableHead));
		table.addCell(new Phrase("MODEL",tableHead));
		table.addCell(new Phrase("NOTE",tableHead));
		
		@SuppressWarnings("unchecked")
		List<Uom> uoms =  (List<Uom>) model.get("uoms");
		
		for(Uom ob: uoms) {
			table.addCell(String.valueOf(ob.getId()));
			table.addCell(ob.getUomType());
			table.addCell(ob.getUomModel());
			table.addCell(ob.getUomDesc());
		}
		
		document.add(table);
		
	}

}
