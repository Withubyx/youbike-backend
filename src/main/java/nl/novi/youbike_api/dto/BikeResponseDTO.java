package nl.novi.youbike_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.novi.youbike_api.model.enums.BikeType;

public class BikeResponseDTO {

    @JsonProperty("bike_id")
    private int id;

    private BikeType bikeType;

    private String brand;

    private String model;

    private String color;

    private String description;

    private BikeImageResponseDTO bikeImage;

    private int ownerId;

    public String ownerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BikeImageResponseDTO getBikeImage() {
        return bikeImage;
    }

    public void setBikeImage(BikeImageResponseDTO bikeImage) {
        this.bikeImage = bikeImage;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
