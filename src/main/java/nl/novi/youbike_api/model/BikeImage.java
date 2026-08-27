package nl.novi.youbike_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bike_images")
public class BikeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    public BikeImage() {}

    public BikeImage(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "BikeImage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
