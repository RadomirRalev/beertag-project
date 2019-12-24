package com.beertag.demo.models.user;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name field may only contain letters.")
    @Size(min = 3)
    @Column(name = "first_name")
    private String firstName; //optional field
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name field may only contain letters.")
    @Size(min = 3)
    @Column(name = "last_name")
    private String lastName; // optional field
    @NotBlank
    @Length(min = 5, max = 50)
    @Pattern(regexp = "^[\\w-]+$", //ограничава позволените символи до букви, цифри, _ и -
            message = "The User Name field may only contain alpha-numeric characters, underscores, and dashes.")
    @Column(name = "nickname")
    private String nickName;
    //https://emailregex.com/
    @Email(regexp = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"
            , message = "Please provide a valid email address.")
    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = userName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String userName) {
        this.nickName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
