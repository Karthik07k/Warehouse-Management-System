package com.logistics.kk.service;

import java.util.List;

import com.logistics.kk.model.Shiping;

public interface IShipingService {

	public Integer saveShiping(Shiping shiping);

	public List<Shiping> getAllShiping();

	public Shiping getOneShiping(Integer id);

	public void updateShipingDtlStatus(Integer id, String status);

}
