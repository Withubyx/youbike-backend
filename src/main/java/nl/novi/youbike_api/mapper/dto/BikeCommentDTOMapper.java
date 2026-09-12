package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeCommentRequestDTO;
import nl.novi.youbike_api.dto.BikeCommentResponseDTO;
import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.BikeComment;
import nl.novi.youbike_api.model.Cyclist;

public class BikeCommentDTOMapper {

    public static BikeComment toEntity(BikeCommentRequestDTO dto) {
        BikeComment bikeComment = new BikeComment();
        bikeComment.setComment(dto.getComment());
        return bikeComment;
    }

    // Method for BikeComment which has the author field with value null
    public static BikeCommentResponseDTO toDto(BikeComment bikeComment, Bike bike) {
        BikeCommentResponseDTO dto = new BikeCommentResponseDTO();
        dto.setId(bikeComment.getId());
        dto.setComment(bikeComment.getComment());
        dto.setCreatedDateTime(bikeComment.getCreatedDateTime());
        dto.setUpdatedDateTime(bikeComment.getUpdatedDateTime());
        dto.setBikeId(bike.getId());
        return dto;
    }

    public static BikeCommentResponseDTO toDto(BikeComment bikeComment, Bike bike, Cyclist author) {
        BikeCommentResponseDTO dto = toDto(bikeComment, bike);
        dto.setAuthorId(author.getId());
        dto.setAuthorName(author.getName());
        return dto;
    }
}
