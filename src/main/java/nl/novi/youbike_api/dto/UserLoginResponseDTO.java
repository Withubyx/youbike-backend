package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.novi.youbike_api.model.enums.UserRole;

public class UserLoginResponseDTO {

    @JsonProperty("bike_company_id")
    private Integer bikeCompanyId;      // A User is either a BikeCompany or a Cyclist, thus one of the Id values is null

    @JsonProperty("cyclist_id")
    private Integer cyclistId;          // A User is either a BikeCompany or a Cyclist, thus one of the Id values is null

    private UserRole role;

    private String name;

    public Integer getBikeCompanyId() {
        return bikeCompanyId;
    }

    public void setBikeCompanyId(Integer bikeCompanyId) {
        this.bikeCompanyId = bikeCompanyId;
    }

    public Integer getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(Integer cyclistId) {
        this.cyclistId = cyclistId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
