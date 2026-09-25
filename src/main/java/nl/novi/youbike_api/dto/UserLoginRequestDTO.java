package nl.novi.youbike_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequestDTO {

    @Email(message = "Insert a valid email address.")
    @NotBlank(message = "Insert an email address. (required)")
    @Size(max = 100, message = "Email address must be at most 100 characters long.")
    private String email;

    @NotBlank(message = "Insert a password. (required)")
    @Size(min = 10, max = 100, message = "Password address must be at least 10 characters long most 100 characters long.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}