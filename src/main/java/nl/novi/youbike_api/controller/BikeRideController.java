package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.service.BikeRideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bike-rides")
public class BikeRideController {

    private final BikeRideService bikeRideService;

    public BikeRideController(BikeRideService bikeRideService) {
        this.bikeRideService = bikeRideService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<BikeRideResponseDTO> createBikeRide(@PathVariable int organizerId, @Valid @RequestBody BikeRideRequestDTO dto) {
        BikeRideResponseDTO responseDTO = bikeRideService.createBikeRide(organizerId, dto);
        URI uri = UriHelper.buildUri(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeRideResponseDTO> updateBikeRide(@PathVariable long id, @Valid @RequestBody BikeRideRequestDTO dto) {
        return ResponseEntity.ok(bikeRideService.updateBikeRide(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeRideResponseDTO> getBikeRide(@PathVariable long id) {
        return ResponseEntity.ok(bikeRideService.getBikeRide(id));
    }

    @GetMapping("/organizers/{id}")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikeRidesByOrganizerId(@PathVariable int id) {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrganizer(id));
    }

    @GetMapping("/start-date-time-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikeRidesByOrderStartDateTimeAscending() {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrderStartDateTimeAscending());
    }

    @GetMapping("/city-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikeRidesByOrderCityAscending() {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrderCityAscending());
    }

    @GetMapping("/distance-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikeRidesByOrderDistanceAscending() {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrderDistanceAscending());
    }

    @GetMapping("/speed-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikeRidesByOrderSpeedAscending() {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrderSpeedAscending());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBikeRide(@PathVariable long id) {
        bikeRideService.deleteBikeRide(id);
        return ResponseEntity.noContent().build();
    }
}
