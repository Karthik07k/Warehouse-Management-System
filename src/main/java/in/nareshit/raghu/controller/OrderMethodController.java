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
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.raghu.exception.OrderMethodNotFound;
import in.nareshit.raghu.model.OrderMethod;
import in.nareshit.raghu.service.IOrderMethodService;
import in.nareshit.raghu.util.OrderMethodUtil;
import in.nareshit.raghu.view.OrderMethodExcelView;

@Controller
@RequestMapping("/om")
public class OrderMethodController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderMethodController.class);
	
	@Autowired
	private IOrderMethodService service;//HAS-A
	
	@Autowired
	private OrderMethodUtil util;
	
	@Autowired
	private ServletContext sc;
	
	//1. show Register
	@GetMapping("/register")
	public String showReg() {
		return "OrderMethodRegister";
	}
	
	//2. save data
	@PostMapping("/save")
	public String saveOrderMethod(
			@ModelAttribute OrderMethod orderMethod,
			Model model
			) 
	{
		LOG.info("ENTERED INTO SAVE METHOD");
		try {
			Integer id = service.saveOrderMethod(orderMethod);
			String msg = " Order Method '"+id+"' created";
			model.addAttribute("message", msg);
			LOG.debug(msg);
		} catch (Exception e) {
			LOG.error("UNABLE TO SAVE {}",e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("ABOUT TO LEAVE SAVE METHOD");
		return "OrderMethodRegister";
	}
	
	///3. fetch data from DB and send to UI
	@GetMapping("/all")
	public String fetchData(Model model) {
		commonSetup(model);
		return "OrderMethodData";
	}
	
	private void commonSetup(Model model) {
		List<OrderMethod> list = service.getAllOrderMethods();
		model.addAttribute("list", list);
	}
	
	//4. delete by id
	@GetMapping("/delete")
	public String deleteById(
			@RequestParam Integer id,
			Model model
			)
	{
		LOG.info("ENTERED INTO DELETE METHOD");
		try {
			service.deleteOrderMethod(id);
			String msg = "Order Method '"+id+"' Deleted!";
			model.addAttribute("message", msg);
			LOG.debug(msg);
		} catch (OrderMethodNotFound e) {
			LOG.error("UNABLE TO DELETE {}",e.getMessage());
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		commonSetup(model);
		LOG.info("ABOUT TO LEAVE DELETE METHOD");
		return "OrderMethodData";
	}
	
	//5. ShowEdit Page
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model
			) 
	{
		LOG.info("ENTERED INTO EDIT METHOD");
		String page = null;
		try {
			OrderMethod om = service.getOneOrderMethod(id);
			model.addAttribute("orderMethod", om);
			page = "OrderMethodEdit";
			LOG.debug("MOVING TO EDIT PAGE");
		} catch (OrderMethodNotFound e) {
			LOG.error("UNABLE TO FETCH FOR EDIT {}",e.getMessage());
			page = "OrderMethodData";
			commonSetup(model);
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		LOG.info("ABOUT TO LEAVE EDIT METHOD");
		return page;
	}
	
	//6. update
	@PostMapping("/update")
	public String update(
			@ModelAttribute OrderMethod orderMethod,
			Model model) 
	{
		service.updateOrderMethod(orderMethod);
		model.addAttribute("message", "Order Method updated!!");
		commonSetup(model);
		return "OrderMethodData";
	}

	//7. excel export
	@GetMapping("/excel")
	public ModelAndView exportData() {
		ModelAndView m = new ModelAndView();
		m.setView(new OrderMethodExcelView()); //view class object
		
		//fetch data form DB
		List<OrderMethod> list = service.getAllOrderMethods();
		m.addObject("list", list);
		
		return m;
	}
	
	//8. generate charts
	@GetMapping("/charts")
	public String generateCharts() {
		List<Object[]> list = service.getOrderMethodModeAndCount();
		String path = sc.getRealPath("/");
		
		util.generatePie(path,list);
		util.generateBar(path,list);
		return "OrderMethodCharts";
	}
}
