package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nareshit.raghu.model.SaleOrder;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {

	// 1. Validate OrderCode(AJEX Validation)

	@Query("SELECT count(orderCode) FROM SaleOrder WHERE orderCode=:code")
	Integer validateOrderCode(String code);

	@Query("SELECT count(orderCode) FROM SaleOrder WHERE orderCode=:code AND id!=:id")
	Integer validateOrderCodeAndId(String code, Integer id);

	// 2. For Status
	@Query("SELECT status FROM SaleOrder WHERE id=:soId")
	String getCurrentStatusOfSaleOrder(Integer soId);

	@Modifying
	@Query("UPDATE SaleOrder SET status=:newStatus WHERE id=:soId")
	void updateSaleOrderStatus(Integer soId, String newStatus);

	// Integration
	@Query("SELECT id,orderCode FROM SaleOrder WHERE status=:status")
	List<Object[]> findSaleOrderIdAndCodeByStatus(String status);

}
