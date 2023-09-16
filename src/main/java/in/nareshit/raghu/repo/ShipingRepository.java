package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nareshit.raghu.model.Shiping;

@Repository
public interface ShipingRepository extends JpaRepository<Shiping, Integer> {

}
