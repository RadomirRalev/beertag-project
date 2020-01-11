package com.beertag.demo.models.user;

import com.beertag.demo.models.beer.Beer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private int enabled;
    @Lob
    @Column(name = "picture", columnDefinition = "BLOB")
    private byte[] picture;

    //TODO
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "authorities",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "authority")
//    )
//    private Set<Role> roles;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wish_beer",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "beer_id"))
    Set<Beer> wishList;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "drank_beer",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "beer_id"))
    Set<Beer> drankList;

    public User() {
    }

    public User(String firstName, String lastName, String userName,
                String email, String password, int enabled, byte[] picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.picture = picture;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<Role> getRoles() {
//        return new HashSet<>(roles);
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<Beer> getWishList() {
        return new HashSet<>(wishList);
    }

    public void setWishList(Set<Beer> wishList) {
        this.wishList = wishList;
    }

    public Set<Beer> getDrankList() {
        return new HashSet<>(drankList);
    }

    public void setDrankList(Set<Beer> drankList) {
        this.drankList = drankList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return Base64.getEncoder().encodeToString(picture);
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}

