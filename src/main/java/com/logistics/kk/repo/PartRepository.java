package com.logistics.kk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logistics.kk.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

	@Query("SELECT id, partCode FROM Part")
	public List<Object[]> getPartIdAndCode();
}
