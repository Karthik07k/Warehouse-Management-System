package com.logistics.kk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistics.kk.model.Part;
import com.logistics.kk.service.IOrderMethodService;
import com.logistics.kk.service.IPartService;
import com.logistics.kk.service.IUomService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService service;
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService omService;
	
	/**
	 * Sends data to create dynamic dropdown for Uom
	 */
	private void commonUi(Model model) {
		model.addAttribute("uoms", uomService.getUomIdAndModel());
		model.addAttribute("oms", omService.getOrderMethodIdAndCode());
	}

	@GetMapping("/register")
	public String showReg(Model model) {
		commonUi(model);
		return "PartRegister";
	}

	@PostMapping("/save")
	public String savePart(
			@ModelAttribute Part part,
			Model model) 
	{
		Integer id  = service.savePart(part);
		model.addAttribute("message", "Part '"+id+"' Created!");
		commonUi(model);
		return "PartRegister";
	}

	//3. display data
	@GetMapping("/all")
	public String displayAll(
			Model model) 
	{
		commonFetchAll(model);
		return "PartData";
	}

	private void commonFetchAll(Model model) {
		model.addAttribute("list", service.getAllParts());
	}
}
