package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.BikeCompanyRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyResponseDTO;
import nl.novi.youbike_api.mapper.dto.BikeCompanyDTOMapper;
import nl.novi.youbike_api.model.BikeCompany;
import nl.novi.youbike_api.model.User;
import nl.novi.youbike_api.repository.BikeCompanyRepository;
import nl.novi.youbike_api.repository.UserRepository;
import nl.novi.youbike_api.service.helper.EmailUniqueHelper;
import nl.novi.youbike_api.service.helper.NameUniqueHelper;
import org.springframework.stereotype.Service;

@Service
public class BikeCompanyService {

    private final BikeCompanyDTOMapper bikeCompanyDTOMapper;
    private final BikeCompanyRepository bikeCompanyRepos;
    private final UserRepository userRepos;
    private final EmailUniqueHelper emailUniqueHelper;
    private final NameUniqueHelper nameUniqueHelper;

    public BikeCompanyService(
            BikeCompanyDTOMapper bikeCompanyDTOMapper,
            BikeCompanyRepository bikeCompanyRepos,
            UserRepository userRepos,
            EmailUniqueHelper emailUniqueHelper,
            NameUniqueHelper nameUniqueHelper) {
        this.bikeCompanyDTOMapper = bikeCompanyDTOMapper;
        this.bikeCompanyRepos = bikeCompanyRepos;
        this.userRepos = userRepos;
        this.emailUniqueHelper = emailUniqueHelper;
        this.nameUniqueHelper = nameUniqueHelper;
    }

    @Transactional
    public BikeCompanyResponseDTO createBikeCompany(BikeCompanyRequestDTO dto) {
        emailUniqueHelper.checkEmailUnique(dto.getEmail().toLowerCase());
        nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());

        BikeCompany bikeCompany = bikeCompanyDTOMapper.toEntity(dto);
        User user = new User(dto.getEmail());
        bikeCompanyRepos.save(bikeCompany);
        user.setBikeCompany(bikeCompany);
        userRepos.save(user);
        return bikeCompanyDTOMapper.toDto(bikeCompany, user);
    }

}
