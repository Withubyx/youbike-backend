package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.BikeCompanyCreateRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyResponseDTO;
import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.BikeCompanyDTOMapper;
import nl.novi.youbike_api.mapper.dto.value_object.AddressLocationDTOMapper;
import nl.novi.youbike_api.model.BikeCompany;
import nl.novi.youbike_api.model.User;
import nl.novi.youbike_api.model.enums.UserRole;
import nl.novi.youbike_api.model.value_object.AddressLocation;
import nl.novi.youbike_api.repository.BikeCompanyRepository;
import nl.novi.youbike_api.repository.UserRepository;
import nl.novi.youbike_api.service.helper.AuthorizationHelper;
import nl.novi.youbike_api.service.helper.EmailUniqueHelper;
import nl.novi.youbike_api.service.helper.NameUniqueHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeCompanyService {

    private final BikeCompanyRepository bikeCompanyRepos;
    private final UserRepository userRepos;
    private final EmailUniqueHelper emailUniqueHelper;
    private final NameUniqueHelper nameUniqueHelper;
    private final BikeRideService bikeRideService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationHelper authorizationHelper;

    public BikeCompanyService(
            BikeCompanyRepository bikeCompanyRepos,
            UserRepository userRepos,
            EmailUniqueHelper emailUniqueHelper,
            NameUniqueHelper nameUniqueHelper,
            BikeRideService bikeRideService,
            PasswordEncoder passwordEncoder, AuthorizationHelper authorizationHelper) {
        this.bikeCompanyRepos = bikeCompanyRepos;
        this.userRepos = userRepos;
        this.emailUniqueHelper = emailUniqueHelper;
        this.nameUniqueHelper = nameUniqueHelper;
        this.bikeRideService = bikeRideService;
        this.passwordEncoder = passwordEncoder;
        this.authorizationHelper = authorizationHelper;
    }

    @Transactional
    public BikeCompanyResponseDTO createBikeCompany(BikeCompanyCreateRequestDTO dto) {
        emailUniqueHelper.checkEmailUnique(dto.getEmail().toLowerCase());
        nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());

        BikeCompany bikeCompany = BikeCompanyDTOMapper.toEntity(dto);
        User user = new User(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.ROLE_BIKE_COMPANY);
        bikeCompanyRepos.save(bikeCompany);
        user.setBikeCompany(bikeCompany);
        userRepos.save(user);
        return BikeCompanyDTOMapper.toDto(bikeCompany, user);
    }

    @Transactional
    public BikeCompanyResponseDTO updateBikeCompany(BikeCompanyRequestDTO dto, UserDetails userDetails) {
        User user = authorizationHelper.getUser(userDetails);
        BikeCompany bikeCompany = authorizationHelper.getBikeCompany(user);
        if (!dto.getName().toLowerCase().equals(bikeCompany.getNameLowercase())) {
            nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());
        }

        BikeCompany bikeCompanyUpdate = BikeCompanyDTOMapper.toEntity(dto);
        bikeCompany.setName(bikeCompanyUpdate.getName());
        bikeCompany.setBikeCompanyType(bikeCompanyUpdate.getBikeCompanyType());
        bikeCompany.setAddressLocation(bikeCompanyUpdate.getAddressLocation());
        return BikeCompanyDTOMapper.toDto(bikeCompany, user);
    }

    @Transactional
    public BikeCompanyResponseDTO updateBikeCompanyAddressLocation(AddressLocationDTO dto, UserDetails userDetails) {
        User user = authorizationHelper.getUser(userDetails);
        BikeCompany bikeCompany = authorizationHelper.getBikeCompany(user);
        AddressLocation addressLocation = AddressLocationDTOMapper.toEntity(dto);

        bikeCompany.setAddressLocation(addressLocation);
        return BikeCompanyDTOMapper.toDto(bikeCompany, user);
    }

    public BikeCompanyResponseDTO getBikeCompany(int bikeCompanyId) {
        return BikeCompanyDTOMapper.toDto(getBikeCompanyByBikeCompanyId(bikeCompanyId), getUserByBikeCompanyId(bikeCompanyId));
    }

    public List<BikeCompanyResponseDTO> getAllBikeCompanies() {
        List<BikeCompany> bikeCompanies = bikeCompanyRepos.findAll();
        List<User> users = bikeCompanies.stream().map(bikeCompany -> getUserByBikeCompanyId(bikeCompany.getId())).toList();

        List<BikeCompanyResponseDTO> dtos = new ArrayList<>();
        for (int i = 0; i < bikeCompanies.size(); i++) {
            dtos.add(BikeCompanyDTOMapper.toDto(bikeCompanies.get(i), users.get(i)));
        }
        return dtos;
    }

    @Transactional
    public void deleteBikeCompany(UserDetails userDetails) {
        User user = authorizationHelper.getUser(userDetails);
        BikeCompany bikeCompany = authorizationHelper.getBikeCompany(user);

        bikeRideService.setOrganizerIdNullIfBikeRideOrganizerIsDeleted(bikeCompany.getId());
        user.setBikeCompany(null);
        userRepos.delete(user);
        bikeCompanyRepos.delete(bikeCompany);
    }


    // Helpers

    private BikeCompany getBikeCompanyByBikeCompanyId(int bikeCompanyId) {
        return bikeCompanyRepos.findById(bikeCompanyId).orElseThrow(() -> new ResourceNotFoundException("Bike Company " + bikeCompanyId + " does not exist."));
    }

    private User getUserByBikeCompanyId(int bikeCompanyId) {
        return userRepos.findByBikeCompany_Id(bikeCompanyId).orElseThrow(() -> new ResourceNotFoundException("User who is Bike Company " + bikeCompanyId + " does not exist."));
    }
}
