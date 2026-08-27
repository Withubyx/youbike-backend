package nl.novi.youbike_api.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "bike_ride_organizers")
public abstract class BikeRideOrganizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "name_lowercase", unique = true, nullable = false, length = 20)
    private String nameLowercase;

    public BikeRideOrganizer() {}

    public BikeRideOrganizer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLowercase() {
        return nameLowercase;
    }

    public void setNameLowercase(String nameLowercase) {
        this.nameLowercase = nameLowercase;
    }

    @Override
    public String toString() {
        return "BikeRideOrganizer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameLowercase='" + nameLowercase + '\'' +
                '}';
    }
}
