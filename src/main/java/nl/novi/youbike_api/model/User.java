package nl.novi.youbike_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "email_lowercase", unique = true, nullable = false, length = 255)
    private String emailLowercase;

    @OneToOne
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @OneToOne
    @JoinColumn(name = "bike_company_id")
    private BikeCompany bikeCompany;

    @Column(name = "was_cyclist")
    private boolean wasCyclist;

    @Column(name = "was_bike_company")
    private boolean wasBikeCompany;

    public User() {}

    public User(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailLowercase() {
        return emailLowercase;
    }

    public void setEmailLowercase(String emailLowercase) {
        this.emailLowercase = emailLowercase;
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        if (cyclist != null) {
            this.cyclist = cyclist;
            if (this.bikeCompany != null) {
                this.wasBikeCompany = true;
                this.bikeCompany = null; // a User can not be both a Cyclist and a BikeCompany
            }
        }
    }

    public void removeCyclist() {
        this.cyclist = null;
        this.wasCyclist = true;
    }

    public BikeCompany getBikeCompany() {
        return bikeCompany;
    }

    public void setBikeCompany(BikeCompany bikeCompany) {
        if (bikeCompany != null) {
            this.bikeCompany = bikeCompany;
            if (this.cyclist != null) {
                this.wasCyclist = true;
                this.cyclist = null; // a User can not be both a Cyclist and a BikeCompany
            }
        }
    }

    public void removeBikeCompany() {
        this.bikeCompany = null;
        this.wasBikeCompany = true;
    }

    public boolean isWasCyclist() {
        return wasCyclist;
    }

    public boolean isWasBikeCompany() {
        return wasBikeCompany;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emailLowercase='" + emailLowercase + '\'' +
                ", wasCyclist=" + wasCyclist +
                ", wasBikeCompany=" + wasBikeCompany +
                '}';
    }
}
