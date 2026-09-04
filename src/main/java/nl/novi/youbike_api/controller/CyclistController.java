package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;
import nl.novi.youbike_api.service.CyclistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/cyclists")
public class CyclistController {

    private final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }

    @PostMapping
    public ResponseEntity<CyclistResponseDTO> createCyclist(@Valid @RequestBody CyclistRequestDTO dto) {
        CyclistResponseDTO responseDTO = cyclistService.createCyclist(dto);
        URI uri = UriHelper.buildUri(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistResponseDTO> updateCyclist(@PathVariable int id, @Valid @RequestBody CyclistRequestDTO dto) {
        return ResponseEntity.ok(cyclistService.updateCyclist(id, dto));
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<CyclistResponseDTO> updateCyclistCityCountryLocation(@PathVariable int id, @Valid @RequestBody CityCountryLocationDTO dto) {
        return ResponseEntity.ok(cyclistService.updateCyclistCityCountryLocation(id, dto));
    }
}
