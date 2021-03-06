package com.beertag.demo.models.beer;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "style")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_id")
    private int id;
    @Column(name = "name")
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Style field may contain only letters.")
    private String name;
    @Column(name = "status")
    int status;

    public Style() {

    }

    public Style(String name) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}