package com.logistics.kk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.logistics.kk.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer>
{

	// For Register
	@Query("SELECT count(orderCode) FROM PurchaseOrder WHERE orderCode=:orderCode")
	Integer getOrderCodeCount(String orderCode);
	
	// For Edit
	@Query("SELECT count(orderCode) FROM PurchaseOrder WHERE orderCode=:orderCode AND id!=:id")
	Integer getOrderCodeCountForEdit(String orderCode,Integer id);
	
	//for status ----
	@Query("SELECT status FROM PurchaseOrder WHERE id=:poId")
	String getCurrentStatusOfPo(Integer poId);
	
	@Modifying
	@Query("UPDATE PurchaseOrder SET status=:newStatus WHERE id=:poId")
	void updatePoStatus(Integer poId,String newStatus);
	
	@Query("SELECT id,orderCode FROM PurchaseOrder WHERE status=:status")
	List<Object[]> getPoIdAndCodesByStatus(String status);
	
}
