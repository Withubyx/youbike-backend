package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeCompanyRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyResponseDTO;
import nl.novi.youbike_api.mapper.dto.value_object.AddressLocationDTOMapper;
import nl.novi.youbike_api.model.BikeCompany;
import nl.novi.youbike_api.model.User;
import org.springframework.stereotype.Component;

public class BikeCompanyDTOMapper {


    public static BikeCompany toEntity(BikeCompanyRequestDTO dto) {
        return new BikeCompany(dto.getName(), dto.getBikeCompanyType(),AddressLocationDTOMapper.toEntity(dto.getAddressLocation()));
    }

    public static BikeCompanyResponseDTO toDto(BikeCompany bikeCompany, User user) {
        BikeCompanyResponseDTO dto = new BikeCompanyResponseDTO();
        dto.setId(bikeCompany.getId());
        dto.setEmail(user.getEmail());
        dto.setName(bikeCompany.getName());
        dto.setBikeCompanyType(bikeCompany.getBikeCompanyType());
        dto.setAddressLocation(AddressLocationDTOMapper.toDto(bikeCompany.getAddressLocation()));
        return dto;
    }
}
