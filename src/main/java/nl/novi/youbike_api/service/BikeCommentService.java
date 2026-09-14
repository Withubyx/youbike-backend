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

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public BikeCommentResponseDTO updateBikeComment(long bikeCommentId, BikeCommentRequestDTO dto) {
        BikeComment bikeComment = getBikeCommentByBikeCommentId(bikeCommentId);

        BikeComment bikeCommentUpdate = BikeCommentDTOMapper.toEntity(dto);
        bikeComment.setComment(bikeCommentUpdate.getComment());
        return returnBikRideResponseDTO(bikeComment);
    }

    public BikeCommentResponseDTO getBikeComment(long bikeCommentId) {
        BikeComment bikeComment = getBikeCommentByBikeCommentId(bikeCommentId);

        return returnBikRideResponseDTO(bikeComment);
    }

    public List<BikeCommentResponseDTO> getAllBikeComments() {
        return returnBikeRideResponseDTOs(bikeCommentRepos.findAll());
    }

    public List<BikeCommentResponseDTO> getAllBikeCommentsByAuthor(int authorId) {
        getAuthorByAuthorId(authorId);

        List<BikeComment> bikeComments = bikeCommentRepos.findByAuthor_IdOrderByCreatedDateTimeAsc(authorId);
        return returnBikeRideResponseDTOs(bikeComments);
    }

    public List<BikeCommentResponseDTO> getAllBikeCommentsByBike(int bikeId) {
        getBikeByBikeId(bikeId);

        List<BikeComment> bikeComments = bikeCommentRepos.findByBike_IdOrderByCreatedDateTimeAsc(bikeId);
        return returnBikeRideResponseDTOs(bikeComments);
    }

    @Transactional
    public void deleteBikeComment(long bikeCommentId) {
        BikeComment bikeComment = getBikeCommentByBikeCommentId(bikeCommentId);
        bikeCommentRepos.delete(bikeComment);
    }


    // Helpers

    public BikeCommentResponseDTO returnBikRideResponseDTO(BikeComment bikeComment) {
        if (bikeComment.getAuthor() == null) {
            return BikeCommentDTOMapper.toDto(bikeComment, bikeComment.getBike());
        }
        return BikeCommentDTOMapper.toDto(bikeComment, bikeComment.getBike(), bikeComment.getAuthor());
    }

    public List<BikeCommentResponseDTO> returnBikeRideResponseDTOs(List<BikeComment> bikeComments) {
        List<BikeCommentResponseDTO> bikeCommentsReturned = new ArrayList<>();
        for (BikeComment bikeComment : bikeComments) {
            bikeCommentsReturned.add(returnBikRideResponseDTO(bikeComment));
        }
        return bikeCommentsReturned;
    }

    private Bike getBikeByBikeId(int bikeId) {
        return bikeRepos.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException("Bike " + bikeId + " does not exist."));
    }

    private Cyclist getAuthorByAuthorId(int authorId) {
        return cyclistRepos.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author who is cyclist " + authorId + " does not exist."));
    }

    public BikeComment getBikeCommentByBikeCommentId(long bikeCommentId) {
        return bikeCommentRepos.findById(bikeCommentId).orElseThrow(() -> new ResourceNotFoundException("Bike comment " + bikeCommentId + " does not exist."));
    }
}
