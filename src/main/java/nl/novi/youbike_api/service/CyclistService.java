package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.CyclistDTOMapper;
import nl.novi.youbike_api.mapper.dto.value_object.CityCountryLocationDTOMapper;
import nl.novi.youbike_api.model.BikeComment;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.User;
import nl.novi.youbike_api.model.value_object.CityCountryLocation;
import nl.novi.youbike_api.repository.BikeCommentRepository;
import nl.novi.youbike_api.repository.CyclistRepository;
import nl.novi.youbike_api.repository.UserRepository;
import nl.novi.youbike_api.service.helper.EmailUniqueHelper;
import nl.novi.youbike_api.service.helper.NameUniqueHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CyclistService {

    private final CyclistRepository cyclistRepos;
    private final UserRepository userRepos;
    private final BikeCommentRepository bikeCommentRepos;
    private final EmailUniqueHelper emailUniqueHelper;
    private final NameUniqueHelper nameUniqueHelper;
    private final BikeRideService bikeRideService;
    private final BikeImageService bikeImageService;

    public CyclistService(
            CyclistRepository cyclistRepos,
            UserRepository userRepos,
            BikeCommentRepository bikeCommentRepos,
            EmailUniqueHelper emailUniqueHelper,
            NameUniqueHelper nameUniqueHelper,
            BikeRideService bikeRideService,
            BikeImageService bikeImageService) {
        this.cyclistRepos = cyclistRepos;
        this.userRepos = userRepos;
        this.bikeCommentRepos = bikeCommentRepos;
        this.emailUniqueHelper = emailUniqueHelper;
        this.nameUniqueHelper = nameUniqueHelper;
        this.bikeRideService = bikeRideService;
        this.bikeImageService = bikeImageService;
    }

    @Transactional
    public CyclistResponseDTO createCyclist(CyclistRequestDTO dto) {
        emailUniqueHelper.checkEmailUnique(dto.getEmail().toLowerCase());
        nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());

        Cyclist cyclist = CyclistDTOMapper.toEntity(dto);
        User user = new User(dto.getEmail());
        cyclistRepos.save(cyclist);
        user.setCyclist(cyclist);
        userRepos.save(user);
        return CyclistDTOMapper.toDto(cyclist, user);
    }

   @Transactional
   public CyclistResponseDTO updateCyclist(int cyclistId, CyclistRequestDTO dto) {
        Cyclist cyclist = getCyclistByCyclistId(cyclistId);
        User user = getUserByCyclistId(cyclistId);
        if (!dto.getEmail().toLowerCase().equals(user.getEmailLowercase())) {
            emailUniqueHelper.checkEmailUnique(dto.getEmail().toLowerCase());
        }
        if (!dto.getName().toLowerCase().equals(cyclist.getNameLowercase())) {
            nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());
        }

        Cyclist cyclistUpdate = CyclistDTOMapper.toEntity(dto);
        cyclist.setName(cyclistUpdate.getName());
        cyclist.setCityCountryLocation(cyclistUpdate.getCityCountryLocation());
        user.setEmail(dto.getEmail());
        return CyclistDTOMapper.toDto(cyclist, user);
    }

   @Transactional
   public CyclistResponseDTO updateCyclistCityCountryLocation(int cyclistId, CityCountryLocationDTO dto) {
       Cyclist cyclist = getCyclistByCyclistId(cyclistId);
       CityCountryLocation cityCountryLocation = CityCountryLocationDTOMapper.toEntity(dto);
       User user = getUserByCyclistId(cyclistId);

       cyclist.setCityCountryLocation(cityCountryLocation);
       return CyclistDTOMapper.toDto(cyclist, user);
    }

    public CyclistResponseDTO getCyclist(int cyclistId) {
        return CyclistDTOMapper.toDto(getCyclistByCyclistId(cyclistId), getUserByCyclistId(cyclistId));
    }

    public List<CyclistResponseDTO> getAllCyclists() {
        List<Cyclist> cyclists = cyclistRepos.findAll();
        List<User> users = cyclists.stream().map(cyclist -> getUserByCyclistId(cyclist.getId())).toList();

        List<CyclistResponseDTO> dtos = new ArrayList<>();
        for (int i = 0; i < cyclists.size(); i++) {
            dtos.add(CyclistDTOMapper.toDto(cyclists.get(i), users.get(i)));
        }
        return dtos;
    }

    @Transactional
    public void deleteCyclist(int cyclistId) throws IOException {
        Cyclist cyclist = getCyclistByCyclistId(cyclistId);
        User user = getUserByCyclistId(cyclistId);

        List<String> bikeImageFileNames = cyclist.getBikes().stream().map(bike -> bike.getBikeImage().getFileName()).toList();
        bikeRideService.setOrganizerIdNullIfBikeRideOrganizerIsDeleted(cyclistId);
        List<BikeComment> bikeComments = bikeCommentRepos.findByAuthor_Id(cyclistId);
        for (BikeComment bikeComment : bikeComments) {
            bikeComment.setAuthor(null);
        }
        user.removeCyclist();
        cyclistRepos.delete(cyclist);
        for (String fileName : bikeImageFileNames) {
            bikeImageService.deleteFile(fileName);
        }
    }


    // Helpers

    private Cyclist getCyclistByCyclistId(int cyclistId) {
        return cyclistRepos.findById(cyclistId).orElseThrow(() -> new ResourceNotFoundException("Cyclist " + cyclistId + " does not exist."));
    }

    private User getUserByCyclistId(int cyclistId) {
        return userRepos.findByCyclist_Id(cyclistId).orElseThrow(() -> new ResourceNotFoundException("User who is Cyclist " + cyclistId + " does not exist."));
    }
}
