package nl.novi.youbike_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import nl.novi.youbike_api.dto.value_object.AddressLocationDTO;
import nl.novi.youbike_api.model.enums.SpeedType;
import nl.novi.youbike_api.model.enums.SurfaceType;

import java.time.LocalDateTime;
import java.util.Set;

public class BikeRideRequestDTO {

    @NotBlank(message = "Insert a title. (required)")
    @Size(max = 40, message = "Title must be at most 40 characters long.")
    private String title;

    @NotNull(message = "Insert a start date and time. (required)")
    @Future(message = "Start date and time must be in the future.")
    private LocalDateTime startDateTime;

    @Size(max = 255, message = "Description must be at most 255 characters long")
    private String description;

    @NotNull(message = "Insert a distance. (required)")
    @Positive(message = "Distance must be a positive number.")
    private Integer distance;

    @NotNull(message = "Choose a speed. (required)")
    private SpeedType speed;

    private Set<SurfaceType> surfaceTypes;

    @Valid
    @NotNull(message = "Insert full address. (required")
    private AddressLocationDTO addressLocation;

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
}
