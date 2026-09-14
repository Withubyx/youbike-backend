package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.mapper.dto.value_object.AddressLocationDTOMapper;
import nl.novi.youbike_api.model.BikeRide;
import nl.novi.youbike_api.model.BikeRideOrganizer;
import org.springframework.stereotype.Component;

public class BikeRideDTOMapper {

    public static BikeRide toEntity(BikeRideRequestDTO dto) {
        BikeRide bikeRide = new BikeRide();
        bikeRide.setTitle(dto.getTitle());
        bikeRide.setStartDateTime(dto.getStartDateTime());
        bikeRide.setDescription(dto.getDescription());
        bikeRide.setDistance(dto.getDistance());
        bikeRide.setSpeed(dto.getSpeed());
        bikeRide.setSurfaceTypes(dto.getSurfaceTypes());
        bikeRide.setAddressLocation(AddressLocationDTOMapper.toEntity(dto.getAddressLocation()));
        return bikeRide;
    }

    // Method for BikeRide which has the organizer field with value null
    public static BikeRideResponseDTO toDto(BikeRide bikeRide) {
        BikeRideResponseDTO dto = new BikeRideResponseDTO();
        dto.setId(bikeRide.getId());
        dto.setTitle(bikeRide.getTitle());
        dto.setStartDateTime(bikeRide.getStartDateTime());
        dto.setDescription(bikeRide.getDescription());
        dto.setDistance(bikeRide.getDistance());
        dto.setSpeed(bikeRide.getSpeed());
        dto.setSurfaceTypes(bikeRide.getSurfaceTypes());
        dto.setAddressLocation(AddressLocationDTOMapper.toDto(bikeRide.getAddressLocation()));
        return dto;
    }

    public static BikeRideResponseDTO toDto(BikeRide bikeRide, BikeRideOrganizer bikeRideOrganizer) {
        BikeRideResponseDTO dto = toDto(bikeRide);
        dto.setOrganizerId(bikeRideOrganizer.getId());
        dto.setOrganizerName(bikeRideOrganizer.getName());
        return dto;
    }
}
