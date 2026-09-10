package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.controller.helper.UriHelper;
import nl.novi.youbike_api.dto.BikeRequestDTO;
import nl.novi.youbike_api.dto.BikeResponseDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.BikeDTOMapper;
import nl.novi.youbike_api.model.Bike;
import nl.novi.youbike_api.model.BikeImage;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.repository.BikeRepository;
import nl.novi.youbike_api.repository.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BikeService {

    private final BikeDTOMapper bikeDTOMapper;
    private final BikeRepository bikeRepos;
    private final CyclistRepository cyclistRepos;
    private final BikeImageService bikeImageService;

    public BikeService(BikeDTOMapper bikeDTOMapper, BikeRepository bikeRepos, CyclistRepository cyclistRepos, BikeImageService bikeImageService) {
        this.bikeDTOMapper = bikeDTOMapper;
        this.bikeRepos = bikeRepos;
        this.cyclistRepos = cyclistRepos;
        this.bikeImageService = bikeImageService;
    }

    @Transactional
    public BikeResponseDTO createBike(int cyclistId, BikeRequestDTO dto, MultipartFile file) throws IOException {
        Cyclist owner = getCyclistByCyclistId(cyclistId);

        String fileName = bikeImageService.storeFile(owner, file);
        BikeImage bikeImage = new BikeImage(fileName);
        Bike bike = bikeDTOMapper.toEntity(dto, owner, bikeImage);
        owner.addBike(bike);
        bikeRepos.save(bike);
        return bikeDTOMapper.toDto(bike, owner, bikeImage, getImageUri(bike));
    }


    // Helpers

    public Cyclist getCyclistByCyclistId(int cyclistId) {
        return cyclistRepos.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Cyclist " + cyclistId + "does not exist."));
    }

    public String getImageUri(Bike bike) {
        return UriHelper.buildUri("/bikes", bike.getId()).toString() + "/image";
    }
}
