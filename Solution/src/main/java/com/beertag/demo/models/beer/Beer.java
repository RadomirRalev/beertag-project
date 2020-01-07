package com.beertag.demo.models.beer;

import com.beertag.demo.models.user.UserDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2, max = 62, message = "Name must be between 2 and 32 characters long")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "beer_country_fk")
    private Country originCountry;
    @ManyToOne
    @JoinColumn(name = "beer_brewery_fk")
    private Brewery brewery;
    @ManyToOne
    @JoinColumn(name = "beer_style_fk")
    private Style style;
    @Column(name = "abv")
    private double abvTag;
    @Lob
    @Column(name = "beer_picture", columnDefinition = "BLOB")
    private byte[] picture;
    @Column(name = "avg_rating")
    private double avgRating;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserDetail createdBy;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "beertag",
            joinColumns = @JoinColumn(name = "beer_beer_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_tag_id")
    )
    private List<Tag> tags;

    public Beer() {

    }

    public Beer(String name, String description, double abvTag, byte[] picture) {
        this.name = name;
        this.description = description;
        this.abvTag = abvTag;
        this.picture = picture;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public double getAbvTag() {
        return abvTag;
    }

    public void setAbvTag(double abvTag) {
        this.abvTag = abvTag;
    }

    public String getPicture() {
        return Base64.getEncoder().encodeToString(picture);
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public UserDetail getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDetail createBy) {
        this.createdBy = createBy;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

}
