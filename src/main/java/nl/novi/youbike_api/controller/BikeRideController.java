package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.service.BikeRideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/bike-rides")
public class BikeRideController {

    private final BikeRideService bikeRideService;

    public BikeRideController(BikeRideService bikeRideService) {
        this.bikeRideService = bikeRideService;
    }

    @PostMapping("{id}")
    public ResponseEntity<BikeRideResponseDTO> createBikeRide(@PathVariable int id, @Valid @RequestBody BikeRideRequestDTO dto) {
        BikeRideResponseDTO responseDTO = bikeRideService.createBikeRide(id, dto);
        URI uri = UriHelper.buildUri(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
