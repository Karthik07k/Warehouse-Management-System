package in.nareshit.raghu.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.raghu.exception.ShipmentTypeNotFoundException;
import in.nareshit.raghu.model.ShipmentType;
import in.nareshit.raghu.service.IShipmentTypeService;
import in.nareshit.raghu.util.ShipmentTypeUtil;
import in.nareshit.raghu.view.ShipmentTypeExcelView;
import in.nareshit.raghu.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShipmentTypeController.class);

	@Autowired
	private IShipmentTypeService service; //HAS-A
	
	@Autowired
	private ShipmentTypeUtil util;
	
	@Autowired
	private ServletContext context;
	

	//1. show Register page
	@GetMapping("/register")
	public String showRegister() {
		return "ShipmentTypeRegister";
	}

	//2. on click submit
	@PostMapping("/save")
	public String saveShipmentType(
			//Read Form Data
			@ModelAttribute ShipmentType shipmentType,
			Model model
			) 
	{
		
		LOG.info("ENTERED INTO SAVE METHOD");
		try {
			//call service
			Integer id = service.saveShipmentType(shipmentType);
			LOG.debug("RECORD IS CREATED WITH ID {}",id);
			
			//success message
			String msg = "Shipment Type '"+id+"' is created";
			//send to UI (key,val)
			model.addAttribute("message", msg);
			
		} catch (Exception e) {
			LOG.error("Unable to process request due to {}", e.getMessage());
			model.addAttribute("message", "Unable to Process Request!");
			e.printStackTrace();
		}

		LOG.info("ABOUT TO GO UI PAGE!");
		//goto UI Page
		return "ShipmentTypeRegister";
	}

	//3. fetch data to UI
	@GetMapping("/all")
	public String fetchShipmentTypes(
			Model model ) 
	{
		
		LOG.info("ENTERED INTO FETCH ALL ROWS");
		try {
			//call service get List<T>
			List<ShipmentType> list = service.getAllShipmentTypes();
			LOG.debug("DATA FOUND WITH SIZE {}", list!=null?list.size():"NO DATA");
			// send data to UI
			model.addAttribute("list", list);
		} catch (Exception e) {
			LOG.error("Unable to fetch data {}",e.getMessage());
			e.printStackTrace();
		}

		LOG.info("MOVING TO DATA PAGE TO DISPLAY");
		//goto UI page
		return "ShipmentTypeData";
	}

	//4. delete by id
	@GetMapping("/delete")
	public String deleteShipmentType(
			@RequestParam Integer id,
			Model model
			) 
	{
		LOG.info("ENTERED INTO DELETE METHOD");
		try {
			//call service
			service.deleteShipmentType(id);
			//create msg
			String msg = "Shipment Type '"+id+"' Deleted!";
			LOG.debug(msg);
			model.addAttribute("message", msg);
			
		} catch (ShipmentTypeNotFoundException e) {
			LOG.error("Unable to process delete Request {}",e.getMessage());
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		
		//load new data
		List<ShipmentType> list = service.getAllShipmentTypes();
		//send data to UI
		model.addAttribute("list", list);
		
		//goto UI page
		return "ShipmentTypeData";
	}
	
	//5. show edit page
	@GetMapping("/edit")
	public String showShipmentTypeEdit(
			@RequestParam Integer id,
			Model model) 
	{
		LOG.info("ENTERED INTO EDIT METHOD");
		String page = null;
		try {
			//fetch from DB using service
			ShipmentType st = service.getShipmentType(id);
			
			//send object to UI as FORM DATA
			model.addAttribute("shipmentType", st);
			
			//show edit if record exist
			page = "ShipmentTypeEdit";
			
		} catch (ShipmentTypeNotFoundException e) {
			LOG.error("Unable to process Edit Request : {}",e.getMessage());
			e.printStackTrace();
			//if row not exist
			page = "ShipmentTypeData";
			model.addAttribute("message", e.getMessage());

			//load new data
			List<ShipmentType> list = service.getAllShipmentTypes();
			//send data to UI
			model.addAttribute("list", list);
			
		}
		LOG.info("ABOUT TO GO PAGE {} ", page);
		//GOTO UI
		return page;
	}
	
	//6. Read Form Data and submit
	@PostMapping("/update")
	public String updateShipmentType(
			@ModelAttribute ShipmentType shipmentType) 
	{
		LOG.info("ENTERED INTO UPDATE METHOD");
		try {
			//call service for update
			service.updateShipmentType(shipmentType);
		} catch (Exception e) {
			LOG.error("Unable to Perform Update : {}",e.getMessage());
			e.printStackTrace();
		}
		LOG.info("REDIRECTING TO FETCH ALL ROWS");
		//redirect to all
		return "redirect:all";
	}

	//7. AJAX Validation
	@GetMapping("/validate")
	@ResponseBody
	public String validateShipmentTypeCode(
			@RequestParam String code,
			@RequestParam Integer id
			) 
	{
		String message="";
		//for register check
		if(id==0 && service.isShipmentTypeCodeExist(code)) {
			message = code + ", already exist";
		} else if(id!=0 && service.isShipmentTypeCodeExistForEdit(code,id)) {
			//for edit check
			message = code + ", already exist";
		}
		
		return message; 
	}
	
	//8. Export to Excel
	@GetMapping("/excel")
	public ModelAndView exportData() {
		ModelAndView m = new ModelAndView();
		m.setView(new ShipmentTypeExcelView()); //view class object
		
		//fetch data form DB
		List<ShipmentType> list = service.getAllShipmentTypes();
		m.addObject("list", list);
		
		return m;
	}
	
	//9. Show charts
	@GetMapping("/charts")
	public String generateCharts() 
	{
		List<Object[]> list =  service.getShipmentTypeModeAndCount();
		String path = context.getRealPath("/"); //root folder
		util.generatePieChart(path, list);
		util.generateBarChart(path, list);
		return "ShipmentTypeCharts";
	}
	
	
	//10. show PDF
	@GetMapping("/pdf")
	public ModelAndView showPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new ShipmentTypePdfView());
		
		m.addObject("list",service.getAllShipmentTypes());
		
		return m;
	}
	
	
}
