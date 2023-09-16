package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nareshit.raghu.model.ShipingDtl;

@Repository
public interface ShipingDetailRepository extends JpaRepository<ShipingDtl, Integer> {

	@Modifying
	@Query("UPDATE ShipingDtl SET status=:status WHERE id=:id")
	void updateShipingDtlStatus(Integer id, String status);

}
