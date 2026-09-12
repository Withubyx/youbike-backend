package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeCommentRequestDTO;
import nl.novi.youbike_api.dto.BikeCommentResponseDTO;
import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.BikeComment;
import nl.novi.youbike_api.model.Cyclist;
import org.springframework.stereotype.Component;

@Component
public class BikeCommentDTOMapper {

    public BikeComment toEntity(BikeCommentRequestDTO dto, Cyclist author, Bike bike) {
        BikeComment bikeComment = new BikeComment();
        bikeComment.setComment(dto.getComment());
        bikeComment.setAuthor(author);
        bikeComment.setBike(bike);
        return bikeComment;
    }

    public BikeCommentResponseDTO toDtoAuthorIsNull(BikeComment bikeComment, Bike bike) {
        BikeCommentResponseDTO dto = new BikeCommentResponseDTO();
        dto.setId(bikeComment.getId());
        dto.setComment(bikeComment.getComment());
        dto.setCreatedDateTime(bikeComment.getCreatedDateTime());
        dto.setUpdatedDateTime(bikeComment.getUpdatedDateTime());
        dto.setBikeId(bike.getId());
        return dto;
    }

    public BikeCommentResponseDTO toDtoAuthorIsNotNull(BikeComment bikeComment, Bike bike, Cyclist author) {
        BikeCommentResponseDTO dto = toDtoAuthorIsNull(bikeComment, bike);
        dto.setAuthorId(author.getId());
        dto.setAuthorName(author.getName());
        return dto;
    }
}
