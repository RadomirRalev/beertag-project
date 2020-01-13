package com.beertag.demo.services;

import com.beertag.demo.models.beer.*;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistrationDTO;
import com.beertag.demo.models.user.UserUpdateDTO;

public class Factory {
    public static final int INDEX = 0;
    public static final String NAME = "X";

    private static final byte[] CDRIVES =
            new byte[] { (byte)0xe0 };

    public static Beer createBeer(){
        return new Beer("Zagorka", "okok", 2.14, CDRIVES);
    }

    public static Beer createBeer2(){
        return new Beer("Shumensko", "okok", 2.14, CDRIVES);
    }

    public static Brewery createBrewery(){
        return new Brewery("Zagorka");
    }

    public static Tag createTag(){
        return new Tag("Tag");
    }

    public static User createUser (){
        return new User("Petar","Petrov", "pesh0","pesho@gmail.com","strong",1,CDRIVES );
    }

    public static UserRegistrationDTO createUserRegistration(){
        return new UserRegistrationDTO("Petar","Petrov",5,12,2002,"pesh0", "pesho@gmail.com","strong");
    }

    public static UserUpdateDTO userUpdateDTO(){
        return new UserUpdateDTO("Petar","Petrov");
    }


    public static Country createCountry(){
        return new Country("Bulgaria");
    }

    public static Style createStyle(){
        return new Style("New Style");
    }

}
