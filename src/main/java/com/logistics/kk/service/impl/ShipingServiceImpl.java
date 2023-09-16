package com.logistics.kk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.kk.exception.ShipingNotFoundException;
import com.logistics.kk.model.Shiping;
import com.logistics.kk.repo.ShipingDetailRepository;
import com.logistics.kk.repo.ShipingRepository;
import com.logistics.kk.service.IShipingService;

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
