package nl.novi.youbike_api.model;

import jakarta.persistence.*;
import nl.novi.youbike_api.model.enums.SpeedType;
import nl.novi.youbike_api.model.enums.SurfaceType;
import nl.novi.youbike_api.model.value_object.AddressLocation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "bike_rides")
public class BikeRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private SpeedType speed;

    @ElementCollection
    @CollectionTable(name = "bike_rides_urface_types", joinColumns = @JoinColumn(name = "bike_ride_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "surface_type")
    private Set<SurfaceType> surfaceTypes = new HashSet<>();

    @Embedded
    private AddressLocation addressLocation;

    // This field is in the constructor, but can later be set to null
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = true)
    private BikeRideOrganizer organizer;

    public BikeRide() {}

    public BikeRide(String title, LocalDateTime startDateTime, Integer distance, SpeedType speed, AddressLocation addressLocation, BikeRideOrganizer organizer) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.distance = distance;
        this.speed = speed;
        this.addressLocation = addressLocation;
        this.organizer = organizer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public SpeedType getSpeed() {
        return speed;
    }

    public void setSpeed(SpeedType speed) {
        this.speed = speed;
    }

    public Set<SurfaceType> getSurfaceTypes() {
        return surfaceTypes;
    }

    public void setSurfaceTypes(Set<SurfaceType> surfaceTypes) {
        this.surfaceTypes = surfaceTypes;
    }

    public void addSurfaceType(SurfaceType surfaceType) {
        surfaceTypes.add(surfaceType);
    }

    public void removeSurfaceType(SurfaceType surfaceType) {
        surfaceTypes.remove(surfaceType);
    }

    public boolean hasSurfaceTypes() {
        return !surfaceTypes.isEmpty();
    }

    public AddressLocation getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(AddressLocation addressLocation) {
        this.addressLocation = addressLocation;
    }

    public BikeRideOrganizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(BikeRideOrganizer organizer) {
        this.organizer = organizer;
    }

    @Override
    public String toString() {
        return "BikeRide{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDateTime=" + startDateTime +
                ", description='" + description + '\'' +
                ", distance=" + distance +
                ", speed=" + speed +
                ", surfaceTypes=" + surfaceTypes +
                ", addressLocation=" + addressLocation +
                '}';
    }
}
