package nl.novi.youbike_api.model;

import jakarta.persistence.*;
import nl.novi.youbike_api.model.enums.BikeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bike_type", nullable = false)
    private BikeType bikeType;

    @Column(nullable = false, length = 20)
    private String brand;

    @Column(nullable = false, length = 20)
    private String model;

    @Column(length = 20)
    private String color;

    @Column(length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Cyclist owner;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bike_image_id", nullable = false)
    private BikeImage bikeImage;

    @OneToMany(mappedBy = "bike", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BikeComment> comments = new ArrayList<>();

    public Bike() {}

    public Bike(BikeType bikeType, String brand, String model, Cyclist owner, BikeImage bikeImage) {
        this.bikeType = bikeType;
        this.brand = brand;
        this.model = model;
        this.owner = owner;
        this.bikeImage = bikeImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Cyclist getOwner() {
        return owner;
    }

    public void setOwner(Cyclist owner) {
        this.owner = owner;
    }

    public BikeImage getBikeImage() {
        return bikeImage;
    }

    public void setBikeImage(BikeImage bikeImage) {
        this.bikeImage = bikeImage;
    }

    public List<BikeComment> getComments() {
        return comments;
    }

    public void addComment(BikeComment bikeComment) {
        bikeComment.setBike(this);
        comments.add(bikeComment);
    }

    public void removeComment(BikeComment bikeComment) {
        comments.remove(bikeComment);
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", bikeType=" + bikeType +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
