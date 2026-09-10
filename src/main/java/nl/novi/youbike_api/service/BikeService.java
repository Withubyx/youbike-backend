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

    // Creates the Bike entity and its BikeImage entity and stores the BikeImage file
    @Transactional
    public BikeResponseDTO createBike(int cyclistId, BikeRequestDTO dto, MultipartFile file) throws IOException {
        Cyclist owner = getCyclistByCyclistId(cyclistId);

        String fileName = bikeImageService.storeFile(owner, file);
        BikeImage bikeImage = new BikeImage(fileName);
        Bike bike = bikeDTOMapper.toEntityForCreate(dto, owner, bikeImage);
        owner.addBike(bike);
        bikeRepos.save(bike);
        return bikeDTOMapper.toDto(bike, owner, bikeImage, getImageUri(bike));
    }

    @Transactional
    public BikeResponseDTO updateBikeInfo(int bikeId, BikeRequestDTO dto) {
        Bike bike = getBikeByBikeId(bikeId);

        Bike bikeUpdate = bikeDTOMapper.toEntityForUpdate(dto);
        bike.setBikeType(bikeUpdate.getBikeType());
        bike.setBrand(bikeUpdate.getBrand());
        bike.setModel(bikeUpdate.getModel());
        bike.setColor(bikeUpdate.getColor());
        bike.setDescription(bikeUpdate.getDescription());
        return bikeDTOMapper.toDto(bike, bike.getOwner(), bike.getBikeImage(), getImageUri(bike));
    }


    // Helpers

    public Bike getBikeByBikeId(int bikeId) {
        return bikeRepos.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException("Bike " + bikeId + " does not exist."));
    }

    public Cyclist getCyclistByCyclistId(int cyclistId) {
        return cyclistRepos.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Cyclist " + cyclistId + "does not exist."));
    }

    public String getImageUri(Bike bike) {
        return UriHelper.buildUri("/bikes", bike.getId()).toString() + "/image";
    }
}
