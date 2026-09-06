package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.novi.youbike_api.dto.value_object.CityCountryLocationDTO;

public class CyclistResponseDTO {

    @JsonProperty("cyclist_id")
    private int id;

    private String email;

    private String name;

    private CityCountryLocationDTO cityCountryLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
