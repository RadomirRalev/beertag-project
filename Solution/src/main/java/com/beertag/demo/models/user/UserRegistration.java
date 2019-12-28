package com.beertag.demo.models.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

public class UserRegistration {
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name field contain may only contain letters.")
    @Size(min = 3)
    @Nullable
    private String firstName; //optional field
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name field may contain only contain letters.")
    @Size(min = 3)
    private String lastName; // optional field

    @Min(1920)
    @Max(2019)
    private int birthYear;

    @Min(1)
    @Max(12)
    private int month;

    @Min(1)
    @Max(31)
    private int day;

    @NotBlank
    @Length(min = 4, max = 30)
    @Pattern(regexp = "^[\\w-]+$", //ограничава позволените символи до букви, цифри, _ и -
            message = "The User Name field may only contain alpha-numeric characters, underscores, and dashes.")
    private String username;

    //https://emailregex.com/
    @Email(regexp = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"
            , message = "Please provide a valid email address.")
    private String email;

    private String password;

    public UserRegistration() {
    }

    public UserRegistration(@Nullable @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name field contain may only contain letters.") @Size(min = 3) String firstName, @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name field may contain only contain letters.") @Size(min = 3) String lastName, @Min(1920) @Max(2019) int birthYear, @Min(1) @Max(12) int month, @Min(1) @Max(31) int day, @NotBlank @Length(min = 4, max = 30) @Pattern(regexp = "^[\\w-]+$", //ограничава позволените символи до букви, цифри, _ и -
            message = "The User Name field may only contain alpha-numeric characters, underscores, and dashes.") String username, @Email(regexp = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"
            , message = "Please provide a valid email address.") String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.month = month;
        this.day = day;
        this.username = username;
        this.email = email;
        this.password = password;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
