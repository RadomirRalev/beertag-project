package com.beertag.demo.models.user;

import com.beertag.demo.models.beer.Beer;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Component
public class UserUpdateDTO {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name field may only contain letters.")
    @Size(min = 3)
    @Column(name = "first_name")
    private String firstName; //optional field
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name field may only contain letters.")
    @Size(min = 3)
    @Column(name = "last_name")
    private String lastName; // optional field

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.equals("")) {
            this.firstName = "empty";
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.equals("")) {
            this.lastName = "empty";
        } else {
            this.lastName = lastName;
        }
    }
}
