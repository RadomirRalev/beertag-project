package com.beertag.demo.models.user;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.role.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

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
    private String username;
    //https://emailregex.com/
    @Email(regexp = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)"
            , message = "Please provide a valid email address.")
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wish_beer",
            joinColumns = @JoinColumn(name = "user_user_id"),
            inverseJoinColumns = @JoinColumn(name = "beer_beer_id"))
    Set<Beer> wishList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "drank_beer",
            joinColumns = @JoinColumn(name = "drank_beer_user_id"),
            inverseJoinColumns = @JoinColumn(name = "drank_beer_beer_id"))
    Set<Beer> drankList;


    public User() {
    }

    public User(String firstName, String lastName, String userName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return new HashSet<>(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
}

