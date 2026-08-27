package nl.novi.youbike_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bike_comments")
public class BikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String comment;

    @CreationTimestamp
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    //This field is in the constructor, but can later be set to null
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private Cyclist author;

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;

    public BikeComment() {}

    public BikeComment(String comment, Cyclist author, Bike bike) {
        this.comment = comment;
        this.author = author;
        this.bike = bike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Cyclist getAuthor() {
        return author;
    }

    public void setAuthor(Cyclist author) {
        this.author = author;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    @Override
    public String toString() {
        return "BikeComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                '}';
    }
}
