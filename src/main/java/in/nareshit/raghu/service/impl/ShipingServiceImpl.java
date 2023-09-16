package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.exception.ShipingNotFoundException;
import in.nareshit.raghu.model.Shiping;
import in.nareshit.raghu.repo.ShipingDetailRepository;
import in.nareshit.raghu.repo.ShipingRepository;
import in.nareshit.raghu.service.IShipingService;

@Service
public class ShipingServiceImpl implements IShipingService {

	@Autowired
	private ShipingRepository repository;

	@Autowired
	private ShipingDetailRepository dtlRepository;

	@Override
	public Integer saveShiping(Shiping shiping) {
		return repository.save(shiping).getId();
	}

	@Override
	public List<Shiping> getAllShiping() {
		return repository.findAll();
	}

	@Override
	public Shiping getOneShiping(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ShipingNotFoundException("Shiping Not Exit:"));
	}

	@Override
	@Transactional
	public void updateShipingDtlStatus(Integer id, String status) {
		dtlRepository.updateShipingDtlStatus(id, status);
	}

}
