package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeCommentRequestDTO;
import nl.novi.youbike_api.dto.BikeCommentResponseDTO;
import nl.novi.youbike_api.service.BikeCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/bike-comments")
public class BikeCommentController {

    private final BikeCommentService bikeCommentService;

    public BikeCommentController(BikeCommentService bikeCommentService) {
        this.bikeCommentService = bikeCommentService;
    }

    @PostMapping("/bikes/{bikeId}/authors/{authorId}")
    public ResponseEntity<BikeCommentResponseDTO> createBikeComment(@PathVariable int bikeId, @PathVariable int authorId, @Valid @RequestBody BikeCommentRequestDTO dto) {
        BikeCommentResponseDTO responseDTO = bikeCommentService.createBikeComment(bikeId,authorId, dto);
        URI uri = UriHelper.buildUri("/bike-comments", responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
