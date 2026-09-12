package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeImageResponseDTO;
import nl.novi.youbike_api.model.BikeImage;
import org.springframework.stereotype.Component;

public class BikeImageDTOMapper {

    public static BikeImageResponseDTO toDto(BikeImage bikeImage, String uri) {
        BikeImageResponseDTO dto = new BikeImageResponseDTO();
        dto.setId(bikeImage.getId());
        dto.setFileName(bikeImage.getFileName());
        dto.setUri(uri);
        return dto;
    }
}
