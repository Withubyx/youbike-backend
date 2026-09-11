package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeRequestDTO;
import nl.novi.youbike_api.dto.BikeResponseDTO;
import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.BikeImage;
import nl.novi.youbike_api.model.Cyclist;
import org.springframework.stereotype.Component;

@Component
public class BikeDTOMapper {

    private final BikeImageDTOMapper bikeImageDTOMapper;

    public BikeDTOMapper(BikeImageDTOMapper bikeImageDTOMapper) {
        this.bikeImageDTOMapper = bikeImageDTOMapper;
    }

    public Bike toEntityForCreate(BikeRequestDTO dto, Cyclist owner, BikeImage bikeImage) {
        Bike bike = new Bike(dto.getBikeType(), dto.getBrand(), dto.getModel(), owner, bikeImage);
        bike.setColor(dto.getColor());
        bike.setDescription(dto.getDescription());
        return bike;
    }

    public Bike toEntityForUpdate(BikeRequestDTO dto) {
        Bike bike = new Bike();
        bike.setBikeType(dto.getBikeType());
        bike.setBrand(dto.getBrand());
        bike.setModel(dto.getModel());
        bike.setColor(dto.getColor());
        bike.setDescription(dto.getDescription());
        return bike;
    }

    public BikeResponseDTO toDto(Bike bike, Cyclist owner, BikeImage bikeImage, String uri) {
        BikeResponseDTO dto = new BikeResponseDTO();
        dto.setId(bike.getId());
        dto.setBikeType(bike.getBikeType());
        dto.setBrand(bike.getBrand());
        dto.setModel(bike.getModel());
        dto.setColor(bike.getColor());
        dto.setDescription(bike.getDescription());
        dto.setBikeImage(bikeImageDTOMapper.toDto(bikeImage, uri));
        dto.setOwnerId(owner.getId());
        dto.setOwnerName(owner.getName());
        return dto;
    }
}
