package com.logistics.kk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.kk.model.Shiping;

@Repository
public interface ShipingRepository extends JpaRepository<Shiping, Integer> {

}
