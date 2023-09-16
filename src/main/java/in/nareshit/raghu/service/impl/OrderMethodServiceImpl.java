package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.OrderMethodNotFound;
import in.nareshit.raghu.model.OrderMethod;
import in.nareshit.raghu.repo.OrderMethodRepository;
import in.nareshit.raghu.service.IOrderMethodService;
import in.nareshit.raghu.util.MyAppUtil;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo;//HAS-A

	public Integer saveOrderMethod(OrderMethod om) {
		return repo.save(om).getId();
	}


	public void updateOrderMethod(OrderMethod om) {
		if(om.getId()==null || !repo.existsById(om.getId()))
			throw new OrderMethodNotFound("Order Method Not Exist");
		repo.save(om).getId();
	}


	public void deleteOrderMethod(Integer id) {
		repo.delete(getOneOrderMethod(id));
	}


	public OrderMethod getOneOrderMethod(Integer id) {
		return repo.findById(id).orElseThrow(
				()->new OrderMethodNotFound("Order Method Not Exist")
				);
	}


	public List<OrderMethod> getAllOrderMethods() {
		return repo.findAll();
	}


	public boolean isOrderMethodCodeExist(String code) {
		return repo.isOrderMethodCodeExist(code) > 0;
	}


	public boolean isOrderMethodCodeExistForEdit(String code, Integer id) {
		return repo.isOrderMethodCodeExistForEdit(code,id) > 0;
	}
	
	public List<Object[]> getOrderMethodModeAndCount() {
		return repo.getOrderMethodModeAndCount();
	}
	
	public Map<Integer, String> getOrderMethodIdAndCode() {
		List<Object[]> list = repo.getOrderMethodIdAndCode();
		return MyAppUtil.convertListToMap(list);
	}

}
