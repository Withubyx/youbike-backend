package nl.novi.youbike_api.model;

import jakarta.persistence.*;
import nl.novi.youbike_api.model.value_object.CityCountryLocation;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cyclists")
public class Cyclist extends BikeRideOrganizer{

    @Embedded
    private CityCountryLocation cityCountryLocation;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bike> bikes = new ArrayList<>();

    public Cyclist() {}

    public Cyclist(String name, CityCountryLocation cityCountryLocation) {
        super(name);
        this.cityCountryLocation = cityCountryLocation;
    }

    public CityCountryLocation getCityCountryLocation() {
        return cityCountryLocation;
    }

    public void setCityCountryLocation(CityCountryLocation cityCountryLocation) {
        this.cityCountryLocation = cityCountryLocation;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void addBike(Bike bike) {
        bike.setOwner(this);
        bikes.add(bike);
    }

    public void removeBike(Bike bike) {
            bikes.remove(bike);
    }

    public boolean hasBikes() {
        return !bikes.isEmpty();
    }

    @Override
    public String toString() {
        return "Cyclist{" +
                "cityCountryLocation=" + cityCountryLocation +
                '}';
    }
}
