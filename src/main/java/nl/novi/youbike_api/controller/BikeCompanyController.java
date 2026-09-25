package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeCompanyCreateRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyResponseDTO;
import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.service.BikeCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bike-companies")
public class BikeCompanyController {

    private final BikeCompanyService bikeCompanyService;

    public BikeCompanyController(BikeCompanyService bikeCompanyService) {
        this.bikeCompanyService = bikeCompanyService;
    }

    @PostMapping
    public ResponseEntity<BikeCompanyResponseDTO> createBikeCompany(@Valid @RequestBody BikeCompanyCreateRequestDTO dto) {
        BikeCompanyResponseDTO responseDTO = bikeCompanyService.createBikeCompany(dto);
        URI uri = UriHelper.buildUri(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping()
    public ResponseEntity<BikeCompanyResponseDTO> updateBikeCompany(
            @Valid @RequestBody BikeCompanyRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(bikeCompanyService.updateBikeCompany(dto, userDetails));
    }

    @PatchMapping("/location")
    public ResponseEntity<BikeCompanyResponseDTO> updateBikeCompanyAddressLocation(
            @Valid @RequestBody AddressLocationDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(bikeCompanyService.updateBikeCompanyAddressLocation(dto, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeCompanyResponseDTO> getBikeCompany(@PathVariable int id) {
        return ResponseEntity.ok(bikeCompanyService.getBikeCompany(id));
    }

    @GetMapping
    public ResponseEntity<List<BikeCompanyResponseDTO>> getAllBikeCompanies() {
        return ResponseEntity.ok(bikeCompanyService.getAllBikeCompanies());
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteBikeCompany(@AuthenticationPrincipal UserDetails userDetails) {
        bikeCompanyService.deleteBikeCompany(userDetails);
        return ResponseEntity.noContent().build();
    }
}
