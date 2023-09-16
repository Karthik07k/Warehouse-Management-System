package com.logistics.kk.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.kk.model.PurchaseDtl;
import com.logistics.kk.model.PurchaseOrder;
import com.logistics.kk.repo.PurchaseDtlRepository;
import com.logistics.kk.repo.PurchaseOrderRepository;
import com.logistics.kk.service.IPurchaseOrderService;
import com.logistics.kk.util.MyAppUtil;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo; //HAS-A
	
	@Autowired
	private PurchaseDtlRepository dtlRepo;

	@Override
	public Integer savePurchaseOrder(PurchaseOrder po) {
		return repo.save(po).getId();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		return repo.findById(id).orElseThrow(()->new RuntimeException("Purchase Order NOT FOUND"));
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}

	@Override
	public boolean isPurchaseOrderCodeExist(String code) {
		return repo.getOrderCodeCount(code)>0;
	}

	@Override
	public boolean isPurchaseOrderCodeExistForEdit(String code, Integer id) {
		return repo.getOrderCodeCountForEdit(code, id)>0;
	}

	//-----------------screen#2----------------------------
	public Integer savePurchaseDtl(PurchaseDtl pdtl) {
		return dtlRepo.save(pdtl).getId();
	}
	
	public List<PurchaseDtl> getPurchaseDtlsByPoId(Integer id) {
		return dtlRepo.getPurchaseDtlsByPoId(id);
	}
	
	public void deletePurchaseDtl(Integer dtlId) {
		if(dtlRepo.existsById(dtlId)) {
			dtlRepo.deleteById(dtlId);
		}
	}
	
	public String getCurrentStatusOfPo(Integer poId) {
		return repo.getCurrentStatusOfPo(poId);
	}
	
	@Transactional
	public void updatePoStatus(Integer poId, String newStatus) {
		repo.updatePoStatus(poId, newStatus);
	}
	
	public Integer getPurchaseDtlsCountByPoId(Integer poId) {
		return dtlRepo.getPurchaseDtlsCountByPoId(poId);
	}
	
	public Optional<PurchaseDtl> getPurchaseDtlByPartIdAndPoId(Integer partId, Integer poId) {
		return dtlRepo.getPurchaseDtlByPartIdAndPoId(partId, poId);
	}
	@Transactional
	public Integer updatePurchaseDtlQtyByDtlId(Integer newQty, Integer dtlId) {
		return dtlRepo.updatePurchaseDtlQtyByDtlId(newQty, dtlId);
	}
	
	public Map<Integer, String> getPoIdAndCodesByStatus(String status) {
		List<Object[]> list = repo.getPoIdAndCodesByStatus(status);
		return MyAppUtil.convertListToMap(list);
	}

}
