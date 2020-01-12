package com.beertag.demo.models.user;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class UserUpdateDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private MultipartFile file;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
