package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.mapper.dto.value_object.AddressLocationDTOMapper;
import nl.novi.youbike_api.model.BikeRide;
import nl.novi.youbike_api.model.BikeRideOrganizer;

public class BikeRideDTOMapper {

    private final AddressLocationDTOMapper addressLocationDTOMapper;

    public BikeRideDTOMapper(AddressLocationDTOMapper addressLocationDTOMapper) {
        this.addressLocationDTOMapper = addressLocationDTOMapper;
    }

    public BikeRide toEntity(BikeRideRequestDTO dto) {
        BikeRide bikeRide = new BikeRide();
        bikeRide.setTitle(dto.getTitle());
        bikeRide.setStartDateTime(dto.getStartDateTime());
        bikeRide.setDescription(dto.getDescription());
        bikeRide.setDistance(dto.getDistance());
        bikeRide.setSpeed(dto.getSpeed());
        bikeRide.setSurfaceTypes(dto.getSurfaceTypes());
        bikeRide.setAddressLocation(addressLocationDTOMapper.toEntity(dto.getAddressLocation()));
        return bikeRide;
    }

    public BikeRideResponseDTO toDto(BikeRide bikeRide, BikeRideOrganizer bikeRideOrganizer) {
        BikeRideResponseDTO dto = new BikeRideResponseDTO();
        dto.setId(bikeRide.getId());
        dto.setTitle(bikeRide.getTitle());
        dto.setStartDateTime(bikeRide.getStartDateTime());
        dto.setDescription(bikeRide.getDescription());
        dto.setDistance(bikeRide.getDistance());
        dto.setSpeed(bikeRide.getSpeed());
        dto.setSurfaceTypes(bikeRide.getSurfaceTypes());
        dto.setAddressLocation(addressLocationDTOMapper.toDto(bikeRide.getAddressLocation()));
        dto.setOrganizerId(bikeRideOrganizer.getId());
        dto.setOrganizerName(bikeRideOrganizer.getName());
        return dto;
    }
}
