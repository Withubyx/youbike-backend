package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeRequestDTO;
import nl.novi.youbike_api.dto.BikeResponseDTO;
import nl.novi.youbike_api.service.BikeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

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
}
