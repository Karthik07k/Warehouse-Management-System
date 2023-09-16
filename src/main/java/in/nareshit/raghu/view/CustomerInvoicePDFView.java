package in.nareshit.raghu.view;

import java.awt.Color;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nareshit.raghu.model.SaleOrder;
import in.nareshit.raghu.model.SaleOrderDetails;

public class CustomerInvoicePDFView extends AbstractPdfView {

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		HeaderFooter header = new HeaderFooter(new Phrase("Invoice PDF By Warehouse"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		HeaderFooter footer = new HeaderFooter(new Phrase(new Date() + " ||      INVOICE DATA    ||     PAGE#"), true);
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		/***
		 * PO, PoDtl data given by Controller based on PoId
		 */
		@SuppressWarnings("unchecked")
		List<SaleOrderDetails> list = (List<SaleOrderDetails>) model.get("list");
		SaleOrder saleOrder = (SaleOrder) model.get("saleOrder");

		/**
		 * Calculating final cost SUM(Every Part : basecost * qty))
		 */
		double finalCost = 0.0;
		DoubleSummaryStatistics dss = new DoubleSummaryStatistics();
		for (SaleOrderDetails dtl : list) {
			// finalCost += (dtl.getQty() * dtl.getPart().getPartBaseCost());
			dss.accept(dtl.getQty() * dtl.getPart().getPartBaseCost());
		}
		finalCost = dss.getSum();

		/***
		 * PDF by default OPEN in browser, Download instead of display file
		 */
		// download file with your own name
		response.addHeader("Content-Disposition", "attachment;filename=CustomerInvoice.pdf");

		/**
		 * One HEADING space before/after
		 */
		Font titleFont = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.RED);
		Paragraph title = new Paragraph("CUSTOMER INVOICE", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(25.0f);
		title.setSpacingBefore(20.0f);
		document.add(title);

		/**
		 * Table#1 Display Vendor Code, Order Code, Final Cost, Shipment
		 */
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.WHITE);
		PdfPTable table1 = new PdfPTable(4);
		table1.setSpacingAfter(4.0f);
		table1.setHorizontalAlignment(Element.ALIGN_CENTER);

		table1.addCell(getCellData("CUSTOMER CODE", tableHead));
		table1.addCell(saleOrder.getCustomer().getUserCode());
		table1.addCell(getCellData("ORDER CODE", tableHead));
		table1.addCell(saleOrder.getOrderCode());

		PdfPTable table2 = new PdfPTable(4);
		table2.setSpacingAfter(4.0f);
		table2.addCell(getCellData("FINAL COST", tableHead));
		table2.addCell(String.valueOf(finalCost));
		table2.addCell(getCellData("SHIPMENT TYPE", tableHead));
		table2.addCell(saleOrder.getSt().getShipCode());

		document.add(table1);
		document.add(table2);

		/**
		 * Table#2 Display Every PoDtl : id, code, cost, qty , lineToal
		 */

		// PdfPTable child = new PdfPTable(4);
		PdfPTable child = new PdfPTable(new float[] { 2.50f, 1.5f, 1.0f, 2.0f });
		child.setSpacingBefore(20.0f);
		child.setSpacingAfter(20.0f);
		child.addCell(getCellData("CODE", tableHead));
		child.addCell(getCellData("BASE COST", tableHead));
		child.addCell(getCellData("QTY", tableHead));
		child.addCell(getCellData("ITEM VALUE", tableHead));

		for (SaleOrderDetails dtl : list) {
			child.addCell(dtl.getPart().getPartCode());
			child.addCell(dtl.getPart().getPartBaseCost().toString());
			child.addCell(dtl.getQty().toString());
			child.addCell(String.valueOf(dtl.getPart().getPartBaseCost() * dtl.getQty()));

		}
		document.add(child);

		/***
		 * Description
		 */
		Paragraph description = new Paragraph("**** CURRENT ORDER CONTAINS " + dss.getCount() + " PARTS ****",
				new Font(Font.TIMES_ROMAN, 14, Font.BOLDITALIC, Color.RED));
		description.setSpacingBefore(10.0f);
		description.setAlignment(Element.ALIGN_CENTER);
		document.add(description);
	}

	/**
	 * Common method for String with background color set
	 */
	private PdfPCell getCellData(String input, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(input, font));
		cell.setBackgroundColor(Color.BLACK);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

}
