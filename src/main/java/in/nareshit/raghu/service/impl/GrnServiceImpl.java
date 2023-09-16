package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.exception.DataNotFoundException;
import in.nareshit.raghu.model.Grn;
import in.nareshit.raghu.repo.GrnDtlReposiory;
import in.nareshit.raghu.repo.GrnReposiory;
import in.nareshit.raghu.service.IGrnService;

@Service
public class GrnServiceImpl implements IGrnService {

	@Autowired
	private GrnReposiory repo;
	
	@Autowired
	private GrnDtlReposiory dtlRepo;
	
	public Integer saveGrn(Grn grn) {
		return repo.save(grn).getId();
	}

	public List<Grn> fetchAllGrns() {
		return repo.findAll();
	}

	public Grn getOneGrn(Integer id) {
		return repo.findById(id)
				.orElseThrow(
						()->new DataNotFoundException("GRN NOT EXIST")
						);
	}
	
	@Transactional
	public void updateGrnDtlStatus(Integer id, String status) {
		dtlRepo.updateGrnDtlStatus(id, status);
	}

}
