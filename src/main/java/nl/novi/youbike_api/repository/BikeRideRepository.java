package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.BikeRide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRideRepository extends JpaRepository<BikeRide, Long> {

    List<BikeRide> findAllByOrganizer_Id(int organizerId);

    List<BikeRide> findAllByOrganizer_IdOrderByStartDateTimeAsc(int organizerId);

    List<BikeRide> findAllByOrganizer_IdOrderByAddressLocationCityAsc(int organizerId);

    List<BikeRide> findAllByOrganizer_IdOrderByDistanceAsc(int organizerId);

    List<BikeRide> findAllByOrganizer_IdOrderBySpeedAsc(int organizerId);

    List<BikeRide> findAllByOrderByStartDateTimeAsc();

    List<BikeRide> findAllByOrderByAddressLocationCityAsc();

    List<BikeRide> findAllByOrderByDistanceAsc();

    List<BikeRide> findAllByOrderBySpeedAsc();
}
