package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.UserLoginResponseDTO;
import nl.novi.youbike_api.model.BikeCompany;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.User;

public class UserDTOMapper {

    public static UserLoginResponseDTO toDto(User user, BikeCompany bikeCompany) {
        UserLoginResponseDTO dto = new UserLoginResponseDTO();
        dto.setBikeCompanyId(bikeCompany.getId());
        dto.setRole(user.getRole());
        dto.setName(bikeCompany.getName());
        return dto;
    }

    public static UserLoginResponseDTO toDto(User user, Cyclist cyclist) {
        UserLoginResponseDTO dto = new UserLoginResponseDTO();
        dto.setCyclistId(cyclist.getId());
        dto.setRole(user.getRole());
        dto.setName(cyclist.getName());
        return dto;
    }
}
