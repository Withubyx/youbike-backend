package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeRideRequestDTO;
import nl.novi.youbike_api.dto.BikeRideResponseDTO;
import nl.novi.youbike_api.service.BikeRideService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PostMapping()
    public ResponseEntity<BikeRideResponseDTO> createBikeRide(
            @Valid @RequestBody BikeRideRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        BikeRideResponseDTO responseDTO = bikeRideService.createBikeRide(dto, userDetails);
        URI uri = UriHelper.buildUri("/bike-rides", responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeRideResponseDTO> updateBikeRide(
            @PathVariable long id,
            @Valid @RequestBody BikeRideRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(bikeRideService.updateBikeRide(id, dto, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeRideResponseDTO> getBikeRide(@PathVariable long id) {
        return ResponseEntity.ok(bikeRideService.getBikeRide(id));
    }

    @GetMapping("/organizers/{organizerId}/start-date-time-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikeRidesByOrganizerIdOrderStartDateTimeAscending(@PathVariable int organizerId) {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrganizerIdOrderStartDateTimeAscending(organizerId));
    }

    @GetMapping("organizers/{organizerId}/city-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikesRidesByOrganizerIdOrderCityAscending(@PathVariable int organizerId) {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrganizerIdOrderCityAscending(organizerId));
    }

    @GetMapping("organizers/{organizerId}/distance-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikesRidesByOrganizerIdOrderDistanceAscending(@PathVariable int organizerId) {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrganizerIdOrderDistanceAscending(organizerId));
    }

    @GetMapping("organizers/{organizerId}/speed-order")
    public ResponseEntity<List<BikeRideResponseDTO>> getAllBikesRidesByOrganizerIdOrderSpeedAscending(@PathVariable int organizerId) {
        return ResponseEntity.ok(bikeRideService.getAllBikeRidesByOrganizerIdOrderSpeedAscending(organizerId));
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
    public ResponseEntity<Void> deleteBikeRide(
            @PathVariable long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        bikeRideService.deleteBikeRide(id, userDetails);
        return ResponseEntity.noContent().build();
    }
}
