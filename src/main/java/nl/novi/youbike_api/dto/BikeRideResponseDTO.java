package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.model.enums.SpeedType;
import nl.novi.youbike_api.model.enums.SurfaceType;

import java.time.LocalDateTime;
import java.util.Set;

public class BikeRideResponseDTO {

    @JsonProperty("bike_ride_id")
    private Long id;

    private String title;

    private LocalDateTime startDateTime;

    private String description;

    private Integer distance;

    private SpeedType speed;

    private Set<SurfaceType> surfaceTypes;

    private AddressLocationDTO addressLocation;

    private Integer organizerId;

    private String organizerName;

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

    public AddressLocationDTO getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(AddressLocationDTO addressLocation) {
        this.addressLocation = addressLocation;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }
}
