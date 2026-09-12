package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.mapper.dto.value_object.CityCountryLocationDTOMapper;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.User;
import org.springframework.stereotype.Component;

public class CyclistDTOMapper {

    public static Cyclist toEntity(CyclistRequestDTO dto) {
        return new Cyclist(dto.getName(), CityCountryLocationDTOMapper.toEntity(dto.getCityCountryLocation()));
    }

    public static CyclistResponseDTO toDto(Cyclist cyclist, User user) {
        CyclistResponseDTO dto = new CyclistResponseDTO();
        dto.setId(cyclist.getId());
        dto.setEmail(user.getEmail());
        dto.setName(cyclist.getName());
        dto.setCityCountryLocation(CityCountryLocationDTOMapper.toDto(cyclist.getCityCountryLocation()));
        return dto;
    }
}
