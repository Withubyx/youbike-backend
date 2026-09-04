package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.BikeRideOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRideOrganizerRepository extends JpaRepository<BikeRideOrganizer, Integer> {

    boolean existsByNameLowercase(String nameLowercase);
}
