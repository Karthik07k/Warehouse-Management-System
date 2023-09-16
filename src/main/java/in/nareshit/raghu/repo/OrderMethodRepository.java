package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.OrderMethod;

public interface OrderMethodRepository 
extends JpaRepository<OrderMethod, Integer> {

	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:code")
	Integer isOrderMethodCodeExist(String code);

	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:code AND id!=:id")
	Integer isOrderMethodCodeExistForEdit(String code, Integer id);

	//for charts
	@Query("SELECT orderMode, COUNT(orderMode) FROM OrderMethod GROUP BY orderMode")
	List<Object[]> getOrderMethodModeAndCount();
	
	//for Integration
	@Query("SELECT id, orderCode FROM OrderMethod ")
	List<Object[]> getOrderMethodIdAndCode();
	
	
}
