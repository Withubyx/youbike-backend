package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.BikeCompanyRequestDTO;
import nl.novi.youbike_api.dto.BikeCompanyResponseDTO;
import nl.novi.youbike_api.mapper.dto.value_object.AddressLocationDTOMapper;
import nl.novi.youbike_api.model.BikeCompany;
import nl.novi.youbike_api.model.User;
import org.springframework.stereotype.Component;

@Component
public class BikeCompanyDTOMapper {

    private final AddressLocationDTOMapper addressLocationDTOMapper;

    public BikeCompanyDTOMapper(AddressLocationDTOMapper addressLocationDTOMapper) {
        this.addressLocationDTOMapper = addressLocationDTOMapper;
    }

    public BikeCompany toEntity(BikeCompanyRequestDTO dto) {
        return new BikeCompany(dto.getName(), dto.getBikeCompanyType(),addressLocationDTOMapper.toEntity(dto.getAddressLocation()));
    }

    public BikeCompanyResponseDTO toDto(BikeCompany bikeCompany, User user) {
        BikeCompanyResponseDTO dto = new BikeCompanyResponseDTO();
        dto.setId(bikeCompany.getId());
        dto.setEmail(user.getEmail());
        dto.setName(bikeCompany.getName());
        dto.setBikeCompanyType(bikeCompany.getBikeCompanyType());
        dto.setAddressLocation(addressLocationDTOMapper.toDto(bikeCompany.getAddressLocation()));
        return dto;
    }
}
