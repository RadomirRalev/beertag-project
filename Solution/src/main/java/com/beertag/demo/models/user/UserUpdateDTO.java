package com.beertag.demo.models.user;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;

//TODO
@Component
public class UserUpdateDTO {
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    private String lastName;

    public UserUpdateDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
