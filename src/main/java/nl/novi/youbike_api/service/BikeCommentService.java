package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.BikeCommentRequestDTO;
import nl.novi.youbike_api.dto.BikeCommentResponseDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.BikeCommentDTOMapper;
import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.BikeComment;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.repository.BikeCommentRepository;
import nl.novi.youbike_api.repository.BikeRepository;
import nl.novi.youbike_api.repository.CyclistRepository;
import org.springframework.stereotype.Service;

@Service
public class BikeCommentService {

    private final BikeCommentRepository bikeCommentRepos;
    private final BikeRepository bikeRepos;
    private final CyclistRepository cyclistRepos;

    public BikeCommentService(BikeCommentRepository bikeCommentRepos, BikeRepository bikeRepos, CyclistRepository cyclistRepos) {
        this.bikeCommentRepos = bikeCommentRepos;
        this.bikeRepos = bikeRepos;
        this.cyclistRepos = cyclistRepos;
    }

    @Transactional
    public BikeCommentResponseDTO createBikeComment(int bikeId, int authorId, BikeCommentRequestDTO dto) {
        Bike bike = getBikeByBikeId(bikeId);
        Cyclist author = getAuthorByAuthorId(authorId);

        BikeComment bikeComment = BikeCommentDTOMapper.toEntity(dto);
        bikeComment.setAuthor(author);
        bikeComment.setBike(bike);
        bike.addComment(bikeComment);
        bikeCommentRepos.save(bikeComment);
        return returnBikRideResponseDTO(bikeComment);
    }


    // Helpers

    public BikeCommentResponseDTO returnBikRideResponseDTO(BikeComment bikeComment) {
        if (bikeComment.getAuthor() == null) {
            return BikeCommentDTOMapper.toDto(bikeComment, bikeComment.getBike());
        }
        return BikeCommentDTOMapper.toDto(bikeComment, bikeComment.getBike(), bikeComment.getAuthor());
    }

    private Bike getBikeByBikeId(int bikeId) {
        return bikeRepos.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException("Bike " + bikeId + " does not exist."));
    }

    private Cyclist getAuthorByAuthorId(int authorId) {
        return cyclistRepos.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author who is cyclist " + authorId + " does not exist."));
    }
}
