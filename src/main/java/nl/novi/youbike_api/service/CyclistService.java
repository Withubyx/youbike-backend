package nl.novi.youbike_api.service;

import jakarta.transaction.Transactional;
import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.mapper.dto.CyclistDTOMapper;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.User;
import nl.novi.youbike_api.repository.CyclistRepository;
import nl.novi.youbike_api.repository.UserRepository;
import nl.novi.youbike_api.service.helper.EmailUniqueHelper;
import nl.novi.youbike_api.service.helper.NameUniqueHelper;
import org.springframework.stereotype.Service;

@Service
public class CyclistService {

    private final CyclistDTOMapper cyclistDTOMapper;
    private final CyclistRepository cyclistRepos;
    private final UserRepository userRepos;
    private final EmailUniqueHelper emailUniqueHelper;
    private final NameUniqueHelper nameUniqueHelper;

    public CyclistService(
            CyclistDTOMapper cyclistDTOMapper,
            CyclistRepository cyclistRepos,
            UserRepository userRepos,
            EmailUniqueHelper emailUniqueHelper,
            NameUniqueHelper nameUniqueHelper) {
        this.cyclistDTOMapper = cyclistDTOMapper;
        this.cyclistRepos = cyclistRepos;
        this.userRepos = userRepos;
        this.emailUniqueHelper = emailUniqueHelper;
        this.nameUniqueHelper = nameUniqueHelper;
    }

    @Transactional
    public CyclistResponseDTO createCyclist(CyclistRequestDTO dto) {
        emailUniqueHelper.checkEmailUnique(dto.getEmail().toLowerCase());
        nameUniqueHelper.checkNameUnique(dto.getName().toLowerCase());

        Cyclist cyclist = cyclistDTOMapper.toEntity(dto);
        User user = new User(dto.getEmail());
        cyclistRepos.save(cyclist);
        user.setCyclist(cyclist);
        userRepos.save(user);
        return cyclistDTOMapper.toDto(cyclist, user);
    }

}
