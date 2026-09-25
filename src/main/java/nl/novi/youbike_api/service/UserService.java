package nl.novi.youbike_api.service;

import nl.novi.youbike_api.dto.UserLoginResponseDTO;
import nl.novi.youbike_api.exception.ResourceNotFoundException;
import nl.novi.youbike_api.mapper.dto.UserDTOMapper;
import nl.novi.youbike_api.model.User;
import nl.novi.youbike_api.model.enums.UserRole;
import nl.novi.youbike_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepos;

    public UserService(UserRepository userRepos) {
        this.userRepos = userRepos;
    }

    public UserLoginResponseDTO fillUserLoginResponseDTO(String email) {
        User user = userRepos.findByEmailLowercase(email.toLowerCase(Locale.ROOT)).orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " does not exist."));
        if (user.getRole().equals(UserRole.ROLE_BIKE_COMPANY)) {
            return UserDTOMapper.toDto(user, user.getBikeCompany());
        }
        return UserDTOMapper.toDto(user, user.getCyclist());
    }
}
