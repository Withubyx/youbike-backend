package nl.novi.youbike_api.model.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import nl.novi.youbike_api.model.enums.CountryCode;

@Embeddable
public class AddressLocation {

    @Column(nullable = false, length = 30)
    private String street;

    @Column(name = "house_number", nullable = false, length = 10)
    private String houseNumber;

    @Column(nullable = false, length = 20)
    private String city;

    @Column(name = "country_code", nullable = false)
    private CountryCode countryCode;

    public AddressLocation() {}

    public AddressLocation(String street, String houseNumber, String city, CountryCode countryCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

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

    @Override
    public String toString() {
        return "AddressLocation{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
