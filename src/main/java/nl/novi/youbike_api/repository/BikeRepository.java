package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
}
