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
import nl.novi.youbike_api.model.enums.BikeType;
import nl.novi.youbike_api.repository.BikeRepository;
import nl.novi.youbike_api.repository.CyclistRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {

    private static Integer lastRandomBikeIndexNumber = -1;

    private final BikeRepository bikeRepos;
    private final CyclistRepository cyclistRepos;
    private final BikeImageService bikeImageService;

    public BikeService(BikeRepository bikeRepos, CyclistRepository cyclistRepos, BikeImageService bikeImageService) {
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
        Bike bike = BikeDTOMapper.toEntity(dto);
        bike.setOwner(owner);
        bike.setBikeImage(bikeImage);
        owner.addBike(bike);
        bikeRepos.save(bike);
        return BikeDTOMapper.toDto(bike, owner, bikeImage, getImageUri(bike));
    }

    @Transactional
    public BikeResponseDTO updateBikeInfo(int bikeId, BikeRequestDTO dto) {
        Bike bike = getBikeByBikeId(bikeId);

        Bike bikeUpdate = BikeDTOMapper.toEntity(dto);
        bike.setBikeType(bikeUpdate.getBikeType());
        bike.setBrand(bikeUpdate.getBrand());
        bike.setModel(bikeUpdate.getModel());
        bike.setColor(bikeUpdate.getColor());
        bike.setDescription(bikeUpdate.getDescription());
        return BikeDTOMapper.toDto(bike, bike.getOwner(), bike.getBikeImage(), getImageUri(bike));
    }

    public BikeResponseDTO getBike(int bikeId) {
        Bike bike =  getBikeByBikeId(bikeId);
        return BikeDTOMapper.toDto(bike, bike.getOwner(), bike.getBikeImage(), getImageUri(bike));
    }

    public BikeResponseDTO getRandomBike() {
        int newRandomBikeIndexNumber;
        List<Bike> bikes = bikeRepos.findAll();
        if (bikes.isEmpty()) throw new ResourceNotFoundException("No bikes registered.");
        if (bikes.size() == 1) {
            lastRandomBikeIndexNumber = 0;
            Bike bike = bikes.get(0);
            return BikeDTOMapper.toDto(bike, bike.getOwner(), bike.getBikeImage(), getImageUri(bike));
        }
        do {newRandomBikeIndexNumber = (int) Math.round(Math.random()*(bikes.size()-1));
        } while (lastRandomBikeIndexNumber.equals(newRandomBikeIndexNumber));
        lastRandomBikeIndexNumber = newRandomBikeIndexNumber;
        Bike bike = bikes.get(newRandomBikeIndexNumber);
        return BikeDTOMapper.toDto(bike, bike.getOwner(), bike.getBikeImage(), getImageUri(bike));
    }

    public List<BikeResponseDTO> getAllBikesByCyclistId(int cyclistId) {
        List<Bike> bikes = bikeRepos.findAllByOwner(getCyclistByCyclistId(cyclistId));
        return returnBikeResponseDTOs(bikes);
    }

    public List<BikeResponseDTO> getAllBikesByBikeType(BikeType bikeType) {
        List<Bike> bikes;
        if (bikeType == null) {
            bikes = bikeRepos.findAll();
        } else {
            bikes = bikeRepos.findByBikeType(bikeType);
        }
        return returnBikeResponseDTOs(bikes);
    }

    public Resource getBikeImage(int bikeId) {
        Bike bike = getBikeByBikeId(bikeId);
        return bikeImageService.downloadFile(bike.getBikeImage().getFileName());
    }

    @Transactional
    public void deleteBike(int bikeId) throws IOException {
        Bike bike = getBikeByBikeId(bikeId);

        bikeRepos.delete(bike);
        bikeImageService.deleteFile(bike.getBikeImage().getFileName());
    }


    // Helpers

    public Bike getBikeByBikeId(int bikeId) {
        return bikeRepos.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException("Bike " + bikeId + " does not exist."));
    }

    public Cyclist getCyclistByCyclistId(int cyclistId) {
        return cyclistRepos.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Cyclist " + cyclistId + "does not exist."));
    }

    public String getImageUri(Bike bike) {
        return UriHelper.buildUri("/bikes", bike.getId()) + "/image";
    }

    public List<BikeResponseDTO> returnBikeResponseDTOs(List<Bike> bikes) {
        List<BikeResponseDTO> bikesReturned = new ArrayList<>();
        for (Bike bike : bikes) {
            bikesReturned.add(BikeDTOMapper.toDto(bike, bike.getOwner(), bike.getBikeImage(), getImageUri(bike)));
        }
        return bikesReturned;
    }
}
