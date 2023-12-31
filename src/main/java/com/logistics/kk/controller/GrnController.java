package com.logistics.kk.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.kk.consts.GrnDtlStatus;
import com.logistics.kk.consts.PurchaseOrderStatus;
import com.logistics.kk.model.Grn;
import com.logistics.kk.model.GrnDtl;
import com.logistics.kk.model.PurchaseDtl;
import com.logistics.kk.service.IGrnService;
import com.logistics.kk.service.IPurchaseOrderService;

@Controller
@RequestMapping("/grn")
public class GrnController {

	@Autowired
	private IGrnService service;

	@Autowired
	private IPurchaseOrderService orderService;

	private void commonUi(Model model) {
		model.addAttribute("pos", 
				orderService.getPoIdAndCodesByStatus(
						PurchaseOrderStatus.INVOICED.name())
				);
	}

	//1. show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
		commonUi(model);
		return "GrnRegister";
	}

	//2. save Grn
	@PostMapping("/save")
	public String saveGrn(
			@ModelAttribute Grn grn,
			Model model) 
	{
		try {
			// prepare GrnDtl and add to Grn
			createGrnDtlsByPo(grn);
			
			// save Grn (also save GrnDtl--CASCADING)
			Integer id = service.saveGrn(grn);
			
			// update po status
			if(id!=null)
				orderService.updatePoStatus(
						((Grn) grn.getPo()).getId(), 
						PurchaseOrderStatus.RECEIVED.name());
			
			//message to ui
			model.addAttribute("message", "GRN '"+id+"' CREATED");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		commonUi(model);
		return "GrnRegister";
	}
	
	private void createGrnDtlsByPo(Grn grn) {
		//read po id from Grn
		Integer poId =  ((Grn) grn.getPo()).getId();
		// get List<PurchaseDtl> by poId
		List<PurchaseDtl> poDtls= orderService.getPurchaseDtlsByPoId(poId);

		// GrnDtls set (empty collection)
		Set<GrnDtl> grnSet = new HashSet<>();
		
		// one PoDtl ---> one GrnDtl 
		for(PurchaseDtl pdtl : poDtls) {
			GrnDtl grnDtl = new GrnDtl();
			grnDtl.setPartCode(pdtl.getPart().getPartCode());
			grnDtl.setBaseCost(pdtl.getPart().getPartBaseCost());
			grnDtl.setQty(pdtl.getQty());

			// add grnDtl to Set<GrnDtl>
			grnSet.add(grnDtl);
		}
		
		grn.setDtls(grnSet);
		//add Set<GrnDtl> to Grn object
		
	}
	
	//3. data page
	//display all GRNs added to DB table
	@GetMapping("/all")
	public String showAll(Model model) 
	{
		List<Grn> grns = service.fetchAllGrns();
		model.addAttribute("list", grns);
		return "GrnData";
	}
	
	
	//4. screen#2
	@GetMapping("/parts")
	public String showGrnDtlsByGrnId(
			@RequestParam Integer id,
			Model model
			)
	{
		Grn grn = service.getOneGrn(id);
		
		model.addAttribute("grn", grn);
		model.addAttribute("list", grn.getDtls());
		
		return "GrnParts";
	}
	
	//5. status update methods
	/**
	 * grnId -- to redirect /parts URL
	 * grnDtlId -- todo status
	 * 
	 */
	@GetMapping("/accept")
	public String updateAccepted(
			@RequestParam Integer id,  //GrnId
			@RequestParam Integer dtlId //grnDtlId
			) 
	{
		service.updateGrnDtlStatus(dtlId, GrnDtlStatus.ACCEPTED.name());
		return "redirect:parts?id="+id;
	}
	
	/**
	 * grnId -- to redirect /parts URL
	 * grnDtlId -- todo status
	 * 
	 */
	@GetMapping("/reject")
	public String updateRejected(
			@RequestParam Integer id,  //GrnId
			@RequestParam Integer dtlId //grnDtlId
			) 
	{
		service.updateGrnDtlStatus(dtlId, GrnDtlStatus.REJECTED.name());
		return "redirect:parts?id="+id;
	}
	
	
}
