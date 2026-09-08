package nl.novi.youbike_api.service;

import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.BikeRideDTOMapper;
import nl.novi.youbike_api.model.BikeRide;
import nl.novi.youbike_api.repository.BikeRideOrganizerRepository;
import nl.novi.youbike_api.repository.BikeRideRepository;
import org.springframework.stereotype.Service;

@Service
public class BikeRideService {

    private final BikeRideDTOMapper bikeRideDTOMapper;
    private final BikeRideRepository bikeRideRepos;
    private final BikeRideOrganizerRepository bikeRideOrganizerRepos;

    public BikeRideService(BikeRideDTOMapper bikeRideDTOMapper, BikeRideRepository bikeRideRepos, BikeRideOrganizerRepository bikeRideOrganizerRepos) {
        this.bikeRideDTOMapper = bikeRideDTOMapper;
        this.bikeRideRepos = bikeRideRepos;
        this.bikeRideOrganizerRepos = bikeRideOrganizerRepos;
    }

    public BikeRideResponseDTO createBikeRide(int bikeRideOrganizerId, BikeRideRequestDTO dto) {
        return returnBikeRideResponseDTO(organizeBikeRideAndAddOrganizer(bikeRideOrganizerId, dto));
    }


    // Helper

    public BikeRide organizeBikeRideAndAddOrganizer(int bikeRideOrganizerId, BikeRideRequestDTO dto) {
        BikeRide bikeRide = bikeRideDTOMapper.toEntity(dto);
    // The field organizer is in the constructor of BikeRide
        bikeRide.setOrganizer(bikeRideOrganizerRepos.findById(bikeRideOrganizerId).orElseThrow(() -> new ResourceNotFoundException("Bike ride organizer " + bikeRideOrganizerId + " does not exist.")));
        bikeRideRepos.save(bikeRide);
        return bikeRide;
    }

    public BikeRideResponseDTO returnBikeRideResponseDTO(BikeRide bikeRide) {
        if (bikeRide.getOrganizer() == null) {
            return bikeRideDTOMapper.toDto(bikeRide);
        }
        return bikeRideDTOMapper.toDto(bikeRide, bikeRide.getOrganizer());
    }
}
