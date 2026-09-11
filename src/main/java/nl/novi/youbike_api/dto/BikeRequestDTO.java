package nl.novi.youbike_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.novi.youbike_api.model.enums.BikeType;

public class BikeRequestDTO {

    @NotNull(message = "Choose a type of bike. (required)")
    private BikeType bikeType;

    @NotBlank(message = "Insert a brand. (required)")
    @Size(max = 20, message = "Bike brand must be at most 20 characters long.")
    private String brand;

    @NotBlank(message = "insert a model. (required")
    @Size(max = 20, message = "Bike model must be at most 20 characters long")
    private String model;

    @Size(max = 20, message = "Bike color must be at most 20 characters long.")
    private String color;

    @Size(max = 255, message = "Bike description must be at most 255 characters long.")
    private String description;

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
}
