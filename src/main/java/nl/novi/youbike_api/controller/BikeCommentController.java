package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeCommentRequestDTO;
import nl.novi.youbike_api.dto.BikeCommentResponseDTO;
import nl.novi.youbike_api.service.BikeCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bike-comments")
public class BikeCommentController {

    private final BikeCommentService bikeCommentService;

    public BikeCommentController(BikeCommentService bikeCommentService) {
        this.bikeCommentService = bikeCommentService;
    }

    @PostMapping("/bikes/{bikeId}")
    public ResponseEntity<BikeCommentResponseDTO> createBikeComment(
            @PathVariable int bikeId,
            @Valid @RequestBody BikeCommentRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        BikeCommentResponseDTO responseDTO = bikeCommentService.createBikeComment(bikeId, dto, userDetails);
        URI uri = UriHelper.buildUri("/bike-comments", responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeCommentResponseDTO> updateBikeComment(
            @PathVariable long id,
            @Valid @RequestBody BikeCommentRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(bikeCommentService.updateBikeComment(id, dto, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeCommentResponseDTO> getBikeComment(@PathVariable long id) {
        return ResponseEntity.ok(bikeCommentService.getBikeComment(id));
    }

    @GetMapping
    public ResponseEntity<List<BikeCommentResponseDTO>> getAllBikeComments() {
        return ResponseEntity.ok(bikeCommentService.getAllBikeComments());
    }

    @GetMapping("authors/{authorId}")
    public ResponseEntity<List<BikeCommentResponseDTO>> getAllBikeCommentsByAuthor(@PathVariable int authorId) {
        return ResponseEntity.ok(bikeCommentService.getAllBikeCommentsByAuthor(authorId));
    }

    @GetMapping("bikes/{bikeId}")
    public ResponseEntity<List<BikeCommentResponseDTO>> getAllBikeCommentsByBike(@PathVariable int bikeId) {
        return ResponseEntity.ok(bikeCommentService.getAllBikeCommentsByBike(bikeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBikeComment(
            @PathVariable long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        bikeCommentService.deleteBikeComment(id, userDetails);
        return ResponseEntity.noContent().build();
    }
}
