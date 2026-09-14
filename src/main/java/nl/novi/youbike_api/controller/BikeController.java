package nl.novi.youbike_api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeRequestDTO;
import nl.novi.youbike_api.dto.BikeResponseDTO;
import nl.novi.youbike_api.model.enums.BikeType;
import nl.novi.youbike_api.service.BikeService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @PostMapping(value = "/cyclists/{cyclistId}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BikeResponseDTO> createBike(@PathVariable int cyclistId, @Valid @RequestPart("bike") BikeRequestDTO dto, @RequestPart("file") MultipartFile file) throws IOException {
        BikeResponseDTO responseDTO = bikeService.createBike(cyclistId, dto, file);
        URI uri = UriHelper.buildUri("/bikes", responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeResponseDTO> updateBikeInfo(@PathVariable int id, @Valid @RequestBody BikeRequestDTO dto) {
        return ResponseEntity.ok(bikeService.updateBikeInfo(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeResponseDTO> getBike(@PathVariable int id) {
        return ResponseEntity.ok(bikeService.getBike(id));
    }

    @GetMapping("/random")
    public ResponseEntity<BikeResponseDTO> getRandomBike() {
        return ResponseEntity.ok(bikeService.getRandomBike());
    }

    @GetMapping("/cyclists/{cyclistId}")
    public ResponseEntity<List<BikeResponseDTO>> getAllBikesByCyclistId(@PathVariable int cyclistId) {
        return ResponseEntity.ok(bikeService.getAllBikesByCyclistId(cyclistId));
    }

    @GetMapping
    public ResponseEntity<List<BikeResponseDTO>> getAllBikesByBikeType(@RequestParam(required = false) BikeType type) {
        return ResponseEntity.ok(bikeService.getAllBikesByBikeType(type));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getBikeImage(@PathVariable int id, HttpServletRequest request) {
        Resource resource = bikeService.getBikeImage(id);

        String mimeType;
        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable int id) throws IOException {
        bikeService.deleteBike(id);
        return ResponseEntity.noContent().build();
    }
}
