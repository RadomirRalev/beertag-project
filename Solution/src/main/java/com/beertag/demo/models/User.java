package com.beertag.demo.models;


import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank // в зависимост о това дали да е nickname или имена, ще сложа някакви ограничения
    private String name; // дали да има 2 имета, или пък това да е nickname ?!
    @NotBlank
    private String email;
//    private  ??? picture; //нямаме знания за тая работа

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
