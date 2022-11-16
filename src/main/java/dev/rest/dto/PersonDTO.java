package dev.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "Username should be not empty")
    @Size(min = 4, max = 60, message = "Username size should be between 4 and 60")
    private String name;

    @NotEmpty(message = "Email should be not empty")
    @Size(min = 4, max = 60, message = "Email size should be between 4 and 60")
    @Email
    private String email;

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 4, max = 255, message = "Password size should be between 4 and 255")
    private String password;

    public PersonDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
