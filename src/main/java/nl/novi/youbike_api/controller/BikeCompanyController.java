package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeCompanyRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyResponseDTO;
import nl.novi.youbike_api.service.BikeCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/bike-companies")
public class BikeCompanyController {

    private final BikeCompanyService bikeCompanyService;

    public BikeCompanyController(BikeCompanyService bikeCompanyService) {
        this.bikeCompanyService = bikeCompanyService;
    }

    @PostMapping
    public ResponseEntity<BikeCompanyResponseDTO> createBikeCompany(@Valid @RequestBody BikeCompanyRequestDTO dto) {
        BikeCompanyResponseDTO responseDTO = bikeCompanyService.createBikeCompany(dto);
        URI uri = UriHelper.buildUri(responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
