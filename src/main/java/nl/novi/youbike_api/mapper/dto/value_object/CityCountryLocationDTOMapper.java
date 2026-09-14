package nl.novi.youbike_api.mapper.dto.value_object;

import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;
import nl.novi.youbike_api.model.value_object.CityCountryLocation;
import org.springframework.stereotype.Component;

public class CityCountryLocationDTOMapper {

    public static CityCountryLocation toEntity(CityCountryLocationDTO dto) {
        return new CityCountryLocation(dto.getCity(), dto.getCountryCode());
    }

    public static CityCountryLocationDTO toDto(CityCountryLocation entity) {
        var result = new CityCountryLocationDTO();
        result.setCity(entity.getCity());
        result.setCountryCode(entity.getCountryCode());
        return result;
    }
}
