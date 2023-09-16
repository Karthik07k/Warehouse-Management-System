package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.nareshit.raghu.model.SaleOrder;
import in.nareshit.raghu.model.SaleOrderDetails;

public interface ISaleOrderService {

	// 1. Save SaleOrder
	public Integer saveSaleOrder(SaleOrder saleOrder);

	// 2. Fetch All SaleOrder
	public List<SaleOrder> getAllSaleOrder();

	// 3. Get SaleOrder based on Id
	public SaleOrder getOneSaleOrder(Integer id);

	// 4. Validate OrderCode(AJEX Validation)
	public boolean validateOrderCode(String code);

	public boolean validateOrderCodeAndId(String code, Integer id);

	// ------------------ Screen#2-------------------
	// 5. Save Purchase Details
	public Integer savePurchaseDetails(SaleOrderDetails saleOrderDetails);

	// 6.
	public List<SaleOrderDetails> getSaleDtlsBySaleOrderId(Integer id);

	// 7. Remove Part
	public void deleteSaleDetails(Integer detailId);

	// 7. For Status
	public String getCurrentStatusOfSaleOrder(Integer soId);

	public void updateSaleOrderStatus(Integer soId, String newStatus);

	public Integer getSaleDtlsCountBySaleOrderId(Integer soId);

	// Increase Part Quantity
	public Optional<SaleOrderDetails> getSaleDetailByPartIdAndSaleOrderId(Integer partId, Integer soId);

	public Integer updateSaleOrderDetailQtyByDetailId(Integer newQty, Integer dtlId);

	// Integration
	Map<Integer, String> findSaleOrderIdAndCodeByStatus(String status);
}
