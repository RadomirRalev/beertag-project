package com.beertag.demo.models.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

public class UserRegistration {
    @NotNull
    private String firstName; //optional field
    @NotNull
    private String lastName; // optional field

    @Min(value = 1920, message = "Birth day is required")
    @Max(2019)
    private int birthYear;

    @Min(value = 1, message = "Birth month is required")
    @Max(12)
    private int birthMonth;

    @Min(value = 1, message = "Birth day is required")
    @Max(31)
    private int birthDay;

    @Length(min = 4, max = 30)
    @Pattern(regexp = "^[\\w-]+$",
            message = "username may only contain alpha-numeric characters, underscores, and dashes.")
    private String username;

    //https://emailregex.com/
    @Email(regexp = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"
            , message = "Please provide a valid email address.")
    private String email;

    @Size(min = 6, message = "Password is required")
    private String password;

    @Size(min = 6, message = "Password is required")
    private String passwordConfirmation;

    public UserRegistration() {
    }

    public UserRegistration(@Nullable @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name field contain may only contain letters.") @Size(min = 3) String firstName, @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name field may contain only contain letters.") @Size(min = 3) String lastName, @Min(1920) @Max(2019) int birthYear, @Min(1) @Max(12) int birthMonth, @Min(1) @Max(31) int birthDay, @NotBlank @Length(min = 4, max = 30) @Pattern(regexp = "^[\\w-]+$", //ограничава позволените символи до букви, цифри, _ и -
            message = "The User Name field may only contain alpha-numeric characters, underscores, and dashes.") String username, @Email(regexp = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"
            , message = "Please provide a valid email address.") String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
