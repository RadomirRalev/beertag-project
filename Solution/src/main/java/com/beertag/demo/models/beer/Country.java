package com.beertag.demo.models.beer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int id;
    @NotBlank
    @Size(min = 3, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Country field may contain only letters.")
    @Column(name = "name")
    private String name;


    public Country() {

    }

    public Country(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
