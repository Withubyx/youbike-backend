package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.CyclistCreateRequestDTO;
import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.CyclistDTOMapper;
import nl.novi.youbike_api.mapper.dto.value_object.CityCountryLocationDTOMapper;
import nl.novi.youbike_api.model.BikeComment;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.User;
import nl.novi.youbike_api.model.enums.UserRole;
import nl.novi.youbike_api.model.value_object.CityCountryLocation;
import nl.novi.youbike_api.repository.BikeCommentRepository;
import nl.novi.youbike_api.repository.CyclistRepository;
import nl.novi.youbike_api.repository.UserRepository;
import nl.novi.youbike_api.service.helper.AuthorizationHelper;
import nl.novi.youbike_api.service.helper.EmailUniqueHelper;
import nl.novi.youbike_api.service.helper.NameUniqueHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationHelper authorizationHelper;

    public CyclistService(
            CyclistRepository cyclistRepos,
            UserRepository userRepos,
            BikeCommentRepository bikeCommentRepos,
            EmailUniqueHelper emailUniqueHelper,
            NameUniqueHelper nameUniqueHelper,
            BikeRideService bikeRideService,
            BikeImageService bikeImageService,
            PasswordEncoder passwordEncoder,
            AuthorizationHelper authorizationHelper) {
        this.cyclistRepos = cyclistRepos;
        this.userRepos = userRepos;
        this.bikeCommentRepos = bikeCommentRepos;
        this.emailUniqueHelper = emailUniqueHelper;
        this.nameUniqueHelper = nameUniqueHelper;
        this.bikeRideService = bikeRideService;
        this.bikeImageService = bikeImageService;
        this.passwordEncoder = passwordEncoder;
        this.authorizationHelper = authorizationHelper;
    }

    @Transactional
    public CyclistResponseDTO createCyclist(CyclistCreateRequestDTO dto) {
        emailUniqueHelper.checkEmailUnique(dto.getEmail().toLowerCase());
        nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());

        Cyclist cyclist = CyclistDTOMapper.toEntity(dto);
        User user = new User(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.ROLE_CYCLIST);
        cyclistRepos.save(cyclist);
        user.setCyclist(cyclist);
        userRepos.save(user);
        return CyclistDTOMapper.toDto(cyclist, user);
    }

   @Transactional
   public CyclistResponseDTO updateCyclist(CyclistRequestDTO dto, UserDetails userDetails) {
        User user = authorizationHelper.getUser(userDetails);
        Cyclist cyclist = authorizationHelper.getCyclist(user);
        if (!dto.getName().toLowerCase().equals(cyclist.getNameLowercase())) {
            nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());
        }

        Cyclist cyclistUpdate = CyclistDTOMapper.toEntity(dto);
        cyclist.setName(cyclistUpdate.getName());
        cyclist.setCityCountryLocation(cyclistUpdate.getCityCountryLocation());
        return CyclistDTOMapper.toDto(cyclist, user);
    }

   @Transactional
   public CyclistResponseDTO updateCyclistCityCountryLocation(CityCountryLocationDTO dto, UserDetails userDetails) {
       User user = authorizationHelper.getUser(userDetails);
       Cyclist cyclist = authorizationHelper.getCyclist(user);
       CityCountryLocation cityCountryLocation = CityCountryLocationDTOMapper.toEntity(dto);

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
    public void deleteCyclist(UserDetails userDetails) throws IOException {
        User user = authorizationHelper.getUser(userDetails);
        Cyclist cyclist = authorizationHelper.getCyclist(user);

        List<String> bikeImageFileNames = cyclist.getBikes().stream().map(bike -> bike.getBikeImage().getFileName()).toList();
        bikeRideService.setOrganizerIdNullIfBikeRideOrganizerIsDeleted(cyclist.getId());
        List<BikeComment> bikeComments = bikeCommentRepos.findByAuthor_Id(cyclist.getId());
        for (BikeComment bikeComment : bikeComments) {
            bikeComment.setAuthor(null);
        }
        user.setCyclist(null);
        userRepos.delete(user);
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
