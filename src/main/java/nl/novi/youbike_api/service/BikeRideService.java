package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.BikeRideDTOMapper;
import nl.novi.youbike_api.model.BikeRide;
import nl.novi.youbike_api.model.BikeRideOrganizer;
import nl.novi.youbike_api.repository.BikeRideOrganizerRepository;
import nl.novi.youbike_api.repository.BikeRideRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeRideService {

    private final BikeRideRepository bikeRideRepos;
    private final BikeRideOrganizerRepository bikeRideOrganizerRepos;

    public BikeRideService(BikeRideRepository bikeRideRepos, BikeRideOrganizerRepository bikeRideOrganizerRepos) {
        this.bikeRideRepos = bikeRideRepos;
        this.bikeRideOrganizerRepos = bikeRideOrganizerRepos;
    }

    @Transactional
    public BikeRideResponseDTO createBikeRide(int bikeRideOrganizerId, BikeRideRequestDTO dto) {
        BikeRide bikeRide = BikeRideDTOMapper.toEntity(dto);
        bikeRide.setOrganizer(bikeRideOrganizerRepos.findById(bikeRideOrganizerId).orElseThrow(() -> new ResourceNotFoundException("Bike ride organizer " + bikeRideOrganizerId + " does not exist.")));
        bikeRideRepos.save(bikeRide);
        return returnBikeRideResponseDTO(bikeRide);
    }

    @Transactional
    public BikeRideResponseDTO updateBikeRide(long bikeRideId, BikeRideRequestDTO dto) {
        BikeRide bikeRide = getBikeRideByBikeRideId(bikeRideId);
        BikeRide bikeRideUpdate = BikeRideDTOMapper.toEntity(dto);
        bikeRide.setTitle(bikeRideUpdate.getTitle());
        bikeRide.setStartDateTime(bikeRideUpdate.getStartDateTime());
        bikeRide.setDescription(bikeRideUpdate.getDescription());
        bikeRide.setDistance(bikeRideUpdate.getDistance());
        bikeRide.setSpeed(bikeRideUpdate.getSpeed());
        bikeRide.setSurfaceTypes(bikeRideUpdate.getSurfaceTypes());
        bikeRide.setAddressLocation(bikeRideUpdate.getAddressLocation());
        return returnBikeRideResponseDTO(bikeRide);
    }

    public BikeRideResponseDTO getBikeRide(long bikeRideId) {
        return returnBikeRideResponseDTO(getBikeRideByBikeRideId(bikeRideId));
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrganizerIdOrderStartDateTimeAscending(int organizerId) {
        getBikeRideOrganizerByOrganizerId(organizerId);

        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrganizer_IdOrderByStartDateTimeAsc(organizerId);
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrganizerIdOrderCityAscending(int organizerId) {
        getBikeRideOrganizerByOrganizerId(organizerId);

        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrganizer_IdOrderByAddressLocationCityAsc(organizerId);
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrganizerIdOrderDistanceAscending(int organizerId) {
        getBikeRideOrganizerByOrganizerId(organizerId);

        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrganizer_IdOrderByDistanceAsc(organizerId);
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrganizerIdOrderSpeedAscending(int organizerId) {
        getBikeRideOrganizerByOrganizerId(organizerId);

        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrganizer_IdOrderBySpeedAsc(organizerId);
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrderStartDateTimeAscending() {
        List<BikeRide> bikeRides =  bikeRideRepos.findAllByOrderByStartDateTimeAsc();
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrderCityAscending() {
        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrderByAddressLocationCityAsc();
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrderDistanceAscending() {
        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrderByDistanceAsc();
        return returnBikeRideResponseDTOs(bikeRides);
    }

    public List<BikeRideResponseDTO> getAllBikeRidesByOrderSpeedAscending() {
        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrderBySpeedAsc();
        return returnBikeRideResponseDTOs(bikeRides);
    }

    @Transactional
    public void deleteBikeRide(long bikeRideId) {
        bikeRideRepos.delete(getBikeRideByBikeRideId(bikeRideId));
    }


    // Helpers

    public BikeRideResponseDTO returnBikeRideResponseDTO(BikeRide bikeRide) {
        if (bikeRide.getOrganizer() == null) {
            return BikeRideDTOMapper.toDto(bikeRide);
        }
        return BikeRideDTOMapper.toDto(bikeRide, bikeRide.getOrganizer());
    }

    public List<BikeRideResponseDTO> returnBikeRideResponseDTOs(List<BikeRide> bikeRides) {
        List<BikeRideResponseDTO> bikeRidesReturned = new ArrayList<>();
        for (BikeRide bikeRide : bikeRides) {
            bikeRidesReturned.add(returnBikeRideResponseDTO(bikeRide));
        }
        return bikeRidesReturned;
    }

    public BikeRide getBikeRideByBikeRideId(long bikeRideId) {
        return bikeRideRepos.findById(bikeRideId).orElseThrow(() -> new ResourceNotFoundException("Bike ride " + bikeRideId + " does not exist."));
    }

    public BikeRideOrganizer getBikeRideOrganizerByOrganizerId(int organizerId) {
        return bikeRideOrganizerRepos.findById(organizerId).orElseThrow(() -> new ResourceNotFoundException("Bike ride organizer " + organizerId + " does not exist."));
    }
}
