package in.nareshit.raghu.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nareshit.raghu.model.SaleOrderDetails;

@Repository
public interface SaleOrderDetailsRepository extends JpaRepository<SaleOrderDetails, Integer> {

	@Query("select saleOrderDetails from SaleOrderDetails saleOrderDetails JOIN saleOrderDetails.saleOrder as saleOrder where saleOrder.id=:poId")
	public List<SaleOrderDetails> getSaleDtlsBySaleOrderId(Integer poId);

	// For Status
	@Query("SELECT count(saleOrderDetails) FROM SaleOrderDetails saleOrderDetails JOIN saleOrderDetails.saleOrder as saleOrder WHERE saleOrder.id=:soId")
	public Integer getSaleDtlsCountBySaleOrderId(Integer soId);

	// Increase Part Quantity
	@Query("SELECT saleOrderDetails FROM SaleOrderDetails  saleOrderDetails JOIN saleOrderDetails.part as part JOIN saleOrderDetails.saleOrder  as saleOrder WHERE part.id=:partId and saleOrder.id=:soId")
	public Optional<SaleOrderDetails> getSaleDetailByPartIdAndSaleOrderId(Integer partId, Integer soId);

	@Modifying
	@Query("UPDATE SaleOrderDetails SET qty = qty + :newQty WHERE id=:dtlId")
	public Integer updateSaleOrderDetailQtyByDetailId(Integer newQty, Integer dtlId);

}
