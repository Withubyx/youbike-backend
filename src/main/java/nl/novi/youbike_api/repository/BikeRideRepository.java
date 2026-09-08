package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.BikeRide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRideRepository extends JpaRepository<BikeRide, Long> {
}
