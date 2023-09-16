package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer>
{

	// For Register
	@Query("SELECT count(uomModel) FROM Uom WHERE uomModel=:uomModel")
	Integer getUomModelCount(String uomModel);
	
	// For Register
	@Query("SELECT count(uomModel) FROM Uom WHERE uomModel=:uomModel and id!=:id")
	Integer getUomModelCountForEdit(String uomModel,Integer id);
	
	//For Integration
	@Query("SELECT id,uomModel FROM Uom")
	List<Object[]> getUomIdAndModel();
}
