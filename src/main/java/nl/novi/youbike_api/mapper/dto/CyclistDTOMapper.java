package nl.novi.youbike_api.mapper.dto;

import nl.novi.youbike_api.dto.CyclistRequestDTO;
import nl.novi.youbike_api.dto.CyclistResponseDTO;
import nl.novi.youbike_api.mapper.dto.value_object.CityCountryLocationDTOMapper;
import nl.novi.youbike_api.model.Cyclist;
import nl.novi.youbike_api.model.User;
import org.springframework.stereotype.Component;

@Component
public class CyclistDTOMapper {

    private final CityCountryLocationDTOMapper cityCountryLocationDTOMapper;

    public CyclistDTOMapper(CityCountryLocationDTOMapper cityCountryLocationDTOMapper) {
        this.cityCountryLocationDTOMapper = cityCountryLocationDTOMapper;
    }

    public Cyclist toEntity(CyclistRequestDTO dto) {
        Cyclist cyclist = new Cyclist();
        cyclist.setName(dto.getName());
        cyclist.setCityCountryLocation(cityCountryLocationDTOMapper.toEntity(dto.getCityCountryLocation()));
        return cyclist;
    }

    public CyclistResponseDTO toDto(Cyclist cyclist, User user) {
        CyclistResponseDTO dto = new CyclistResponseDTO();
        dto.setId(cyclist.getId());
        dto.setEmail(user.getEmail());
        dto.setName(cyclist.getName());
        dto.setCityCountryLocation(cityCountryLocationDTOMapper.toDto(cyclist.getCityCountryLocation()));
        return dto;
    }
}
