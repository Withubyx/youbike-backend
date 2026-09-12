package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeRequestDTO;
import nl.novi.youbike_api.dto.BikeResponseDTO;
import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.BikeImage;
import nl.novi.youbike_api.model.Cyclist;

public class BikeDTOMapper {


    public static Bike toEntity(BikeRequestDTO dto) {
        Bike bike = new Bike();
        bike.setBikeType(dto.getBikeType());
        bike.setBrand(dto.getBrand());
        bike.setModel(dto.getModel());
        bike.setColor(dto.getColor());
        bike.setDescription(dto.getDescription());
        return bike;
    }

    public static BikeResponseDTO toDto(Bike bike, Cyclist owner, BikeImage bikeImage, String uri) {
        BikeResponseDTO dto = new BikeResponseDTO();
        dto.setId(bike.getId());
        dto.setBikeType(bike.getBikeType());
        dto.setBrand(bike.getBrand());
        dto.setModel(bike.getModel());
        dto.setColor(bike.getColor());
        dto.setDescription(bike.getDescription());
        dto.setBikeImage(BikeImageDTOMapper.toDto(bikeImage, uri));
        dto.setOwnerId(owner.getId());
        dto.setOwnerName(owner.getName());
        return dto;
    }
}
