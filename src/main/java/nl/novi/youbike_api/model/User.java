package nl.novi.youbike_api.model;

import jakarta.persistence.*;
import nl.novi.youbike_api.model.enums.UserRole;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "email_lowercase", unique = true, nullable = false, length = 100)
    private String emailLowercase;

    @Column(nullable = false, length = 100)
    private String password;

    private UserRole role;

    @OneToOne
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @OneToOne
    @JoinColumn(name = "bike_company_id")
    private BikeCompany bikeCompany;

    public User() {}

    public User(String email) {
        this.email = email;
        this.emailLowercase = email.toLowerCase();
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
        this.emailLowercase = email.toLowerCase();
    }

    public String getEmailLowercase() {
        return emailLowercase;
    }

    public void setEmailLowercase(String emailLowercase) {
        this.emailLowercase = emailLowercase;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        this.cyclist = cyclist;
        this.bikeCompany = null; // A User is either a BikeCompany or a Cyclist
    }

    public BikeCompany getBikeCompany() {
        return bikeCompany;
    }

    public void setBikeCompany(BikeCompany bikeCompany) {
        this.bikeCompany = bikeCompany;
        this.cyclist = null; // A User is either a BikeCompany or a Cyclist
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emailLowercase='" + emailLowercase + '\'' +
                ", role=" + role +
                '}';
    }
}
