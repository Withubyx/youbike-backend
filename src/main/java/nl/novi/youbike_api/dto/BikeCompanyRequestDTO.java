package nl.novi.youbike_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.model.enums.BikeCompanyType;

public class BikeCompanyRequestDTO {

    @Email(message = "Insert a valid email address.")
    @NotBlank(message = "Insert an email address. (required)")
    @Size(max = 255, message = "Email address must be at most 255 characters long.")
    private String email;   //is a User field

    @NotBlank(message = "Insert a name. (required)")
    @Size(min = 2, max = 20, message = "Name must be at least 2 characters long and at most 20 characters long.")
    private String name;

    @NotNull(message = "Choose a type of company. (required)")
    private BikeCompanyType bikeCompanyType;

    @Valid
    @NotNull(message = "Insert full address. (required)")
    private AddressLocationDTO addressLocation;

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
