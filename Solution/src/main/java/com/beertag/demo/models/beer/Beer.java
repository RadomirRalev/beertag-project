package com.beertag.demo.models.beer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private int id;
    @Column(name = "name")
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
    private String abvTag;
    @Column(name = "beer_picture")
    private String picture;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "beertag",
            joinColumns = @JoinColumn(name = "beer_beer_id"),
            inverseJoinColumns = @JoinColumn(name = "beertag_id")
    )

    private List<Tag> tags;

    public Beer() {

    }

    public Beer(String name, String description, String abvTag, String picture) {
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

    public String getAbvTag() {
        return abvTag;
    }

    public void setAbvTag(String abvTag) {
        this.abvTag = abvTag;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
