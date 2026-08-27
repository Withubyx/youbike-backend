package nl.novi.youbike_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nl.novi.youbike_api.model.enums.BikeCompanyType;
import nl.novi.youbike_api.model.value_object.AddressLocation;

@Entity
@Table(name = "bike_companies")
public class BikeCompany extends BikeRideOrganizer{

    @Column(name = "bike_company_type", nullable = false)
    private BikeCompanyType bikeCompanyType;

    @Embedded
    private AddressLocation addressLocation;

    public BikeCompany() {}

    public BikeCompany(String name, BikeCompanyType bikeCompanyType, AddressLocation addressLocation) {
        super(name);
        this.bikeCompanyType = bikeCompanyType;
        this.addressLocation = addressLocation;
    }

    public BikeCompanyType getBikeCompanyType() {
        return bikeCompanyType;
    }

    public void setBikeCompanyType(BikeCompanyType bikeCompanyType) {
        this.bikeCompanyType = bikeCompanyType;
    }

    public AddressLocation getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(AddressLocation addressLocation) {
        this.addressLocation = addressLocation;
    }

    @Override
    public String toString() {
        return "BikeCompany{" +
                "bikeCompanyType=" + bikeCompanyType +
                ", addressLocation=" + addressLocation +
                '}';
    }
}
