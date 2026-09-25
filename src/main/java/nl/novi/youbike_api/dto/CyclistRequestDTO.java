package nl.novi.youbike_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;

public class CyclistRequestDTO {

    @NotBlank(message = "Insert a name. (required)")
    @Size(min = 2, max = 20, message = "Name must be at least 2 characters long and at most 20 characters long.")
    private String name;

    @Valid
    @NotNull(message = "Insert both city and country. (required)")
    private CityCountryLocationDTO cityCountryLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityCountryLocationDTO getCityCountryLocation() {
        return cityCountryLocation;
    }

    public void setCityCountryLocation(CityCountryLocationDTO cityCountryLocation) {
        this.cityCountryLocation = cityCountryLocation;
    }
}
