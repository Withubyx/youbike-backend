package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.model.enums.BikeCompanyType;

public class BikeCompanyResponseDTO {

    @JsonProperty("bike_company_id")
    private int id;

    private String email;

    private String name;

    private BikeCompanyType bikeCompanyType;

    private AddressLocationDTO addressLocation;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
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

    public BikeCompanyType getBikeCompanyType() {
        return bikeCompanyType;
    }

    public void setBikeCompanyType(BikeCompanyType bikeCompanyType) {
        this.bikeCompanyType = bikeCompanyType;
    }

    public AddressLocationDTO getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(AddressLocationDTO addressLocation) {
        this.addressLocation = addressLocation;
    }
}
