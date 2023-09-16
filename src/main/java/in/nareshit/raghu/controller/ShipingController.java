package in.nareshit.raghu.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import in.nareshit.raghu.consts.SaleOrderStatus;
import in.nareshit.raghu.consts.ShipingDetailStatus;
import in.nareshit.raghu.model.SaleOrderDetails;
import in.nareshit.raghu.model.Shiping;
import in.nareshit.raghu.model.ShipingDtl;
import in.nareshit.raghu.service.ISaleOrderService;
import in.nareshit.raghu.service.IShipingService;

@Controller
@RequestMapping("/shiping")
public class ShipingController {

	private static final Logger log = LoggerFactory.getLogger(ShipingController.class);

	@Autowired
	private IShipingService service;

	@Autowired
	private ISaleOrderService orderService;

	// Dynamic Drop down
	private void commonUI(Model model) {
		model.addAttribute("sos", orderService.findSaleOrderIdAndCodeByStatus(SaleOrderStatus.INVOICED.name()));
	}

	// Show Register Page
	@GetMapping("/register")
	public String showShipingRegisterPage(Model model) {
		log.info("Inside showShipingRegisterPage():");
		commonUI(model);
		return "shipingRegister";
	}

	// Save Shiping
	@PostMapping("/save")
	public String saveShiping(@ModelAttribute Shiping shiping, Model model) {
		log.info("Inside saveShiping():");
		try {
			// prepare ShipingDetail and add to Shiping
			createShipingDetailsBySaleOrder(shiping);
			Integer id = service.saveShiping(shiping);
			// update Sale order status
			if (id != null)
				orderService.updateSaleOrderStatus(shiping.getSo().getId(), SaleOrderStatus.SHIPPED.name());

			model.addAttribute("message", "Shiping Created :" + id);
		} catch (Exception e) {
			log.error("Exception inside saveShiping():" + e.getMessage());
			e.printStackTrace();
		}
		commonUI(model);
		log.info("About shipingRegister UI Page:");
		return "shipingRegister";
	}

	private void createShipingDetailsBySaleOrder(Shiping shiping) {

		Integer soId = shiping.getSo().getId();

		List<SaleOrderDetails> list = orderService.getSaleDtlsBySaleOrderId(soId);

		Set<ShipingDtl> shipingSet = new HashSet<>();

		for (SaleOrderDetails sdtl : list) {

			ShipingDtl shipingDtl = new ShipingDtl();
			shipingDtl.setPartCode(sdtl.getPart().getPartCode());
			shipingDtl.setBaseCost(sdtl.getPart().getPartBaseCost());
			shipingDtl.setQty(sdtl.getQty());

			shipingSet.add(shipingDtl);
		}
		shiping.setDtls(shipingSet);
	}

	// Fetch All Shiping
	@GetMapping("/all")
	public String showAllShiping(Model model) {
		log.info("Inside showAllShiping():");
		try {
			List<Shiping> list = service.getAllShiping();
			model.addAttribute("list", list);
		} catch (Exception e) {
			log.error("Exception inside showAllShiping():" + e.getMessage());
			e.printStackTrace();
		}
		log.info("About ShipingData UI Page:");
		return "ShipingData";
	}

	// ------------------- Screen#2------------------------------------------------
	@GetMapping("/parts")
	public String showShipingDetailByShipingId(@RequestParam Integer id, Model model) {
		log.info("Inside showShipingDetailByShipingId():");
		Shiping shiping = service.getOneShiping(id);
		model.addAttribute("shiping", shiping);
		model.addAttribute("list", shiping.getDtls());
		return "shipingParts";
	}

	// ----------------------- UPDATE Status---------------------------------
	@GetMapping("/accept")
	public String updateAccepted(@RequestParam Integer id, @RequestParam Integer dtlId) {
		service.updateShipingDtlStatus(dtlId, ShipingDetailStatus.RECEIVED.name());
		return "redirect:parts?id=" + id;
	}

	@GetMapping("/reject")
	public String updateRejected(@RequestParam Integer id, @RequestParam Integer dtlId) {
		service.updateShipingDtlStatus(dtlId, ShipingDetailStatus.RETURNED.name());
		return "redirect:parts?id=" + id;
	}

}
