package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyclistRepository extends JpaRepository<Cyclist, Integer> {
}
