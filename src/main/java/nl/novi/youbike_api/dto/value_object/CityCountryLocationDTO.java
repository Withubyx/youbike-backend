package nl.novi.youbike_api.dto.value_object;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.novi.youbike_api.model.enums.CountryCode;

public class CityCountryLocationDTO {

    @NotBlank(message = "Insert a city name. (required)")
    @Size(min = 2, max = 20, message = "City name must be at least 2 characters long and at most 20 characters long.")
    private String city;

    @NotNull(message = "Choose a country. (required)")
    private CountryCode countryCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }
}
