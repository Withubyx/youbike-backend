package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.enums.BikeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> findAllByOwner(Cyclist owner);

    List<Bike> findByBikeType(BikeType bikeType);
}
