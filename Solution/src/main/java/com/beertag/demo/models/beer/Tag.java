package com.beertag.demo.models.beer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;
    @NotBlank
    @Size(min = 3, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Tag field may contain only letters.")
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "beertag",
            joinColumns = @JoinColumn(name = "tag_tag_id"),
            inverseJoinColumns = @JoinColumn(name = "beer_beer_id")
    )
    @JsonIgnore
    private List<Beer> beers;

    public Tag() {
    }

    public Tag(String name) {
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

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
