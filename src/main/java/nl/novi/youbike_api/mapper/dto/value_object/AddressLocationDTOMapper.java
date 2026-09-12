package nl.novi.youbike_api.mapper.dto.value_object;

import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.model.value_object.AddressLocation;
import org.springframework.stereotype.Component;

public class AddressLocationDTOMapper {

    public static AddressLocation toEntity(AddressLocationDTO dto) {
        return new AddressLocation(dto.getStreet(), dto.getHouseNumber(), dto.getCity(), dto.getCountryCode());
    }

    public static AddressLocationDTO toDto(AddressLocation entity) {
        var result = new AddressLocationDTO();
        result.setStreet(entity.getStreet());
        result.setHouseNumber(entity.getHouseNumber());
        result.setCity(entity.getCity());
        result.setCountryCode(entity.getCountryCode());
        return result;
    }
}
