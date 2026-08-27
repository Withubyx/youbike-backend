package nl.novi.youbike_api.model.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import nl.novi.youbike_api.model.enums.CountryCode;

@Embeddable
public class CityCountryLocation {

    @Column(nullable = false, length = 20)
    private String city;

    @Column(name = "country_code", nullable = false)
    private CountryCode countryCode;

    public CityCountryLocation() {}

    public CityCountryLocation(String city, CountryCode countryCode) {
        this.city = city;
        this.countryCode =countryCode;
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
        return "CityCountryLocation{" +
                "city='" + city + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
