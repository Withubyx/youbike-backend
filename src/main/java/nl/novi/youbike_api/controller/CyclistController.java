package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.CyclistCreateRequestDTO;
import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;
import nl.novi.youbike_api.service.CyclistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cyclists")
public class CyclistController {

    private final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @PostMapping
    public ResponseEntity<CyclistResponseDTO> createCyclist(@Valid @RequestBody CyclistCreateRequestDTO dto) {
        CyclistResponseDTO responseDTO = cyclistService.createCyclist(dto);
        URI uri = UriHelper.buildUri(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping()
    public ResponseEntity<CyclistResponseDTO> updateCyclist(
            @Valid @RequestBody CyclistRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cyclistService.updateCyclist(dto, userDetails));
    }

    @PatchMapping("/location")
    public ResponseEntity<CyclistResponseDTO> updateCyclistCityCountryLocation(
            @Valid @RequestBody CityCountryLocationDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cyclistService.updateCyclistCityCountryLocation(dto, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistResponseDTO> getCyclist(@PathVariable int id) {
        return ResponseEntity.ok(cyclistService.getCyclist(id));
    }

    @GetMapping
    public ResponseEntity<List<CyclistResponseDTO>> gelAllCyclists() {
        return ResponseEntity.ok(cyclistService.getAllCyclists());
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteCyclist(@AuthenticationPrincipal UserDetails userDetails) throws IOException {
        cyclistService.deleteCyclist(userDetails);
        return ResponseEntity.noContent().build();
    }
}
