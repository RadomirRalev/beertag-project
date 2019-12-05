package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.repositories.BeersRepository;
import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.services.Factory.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceImplTests {
    @Mock
    BeersRepository repository;

    @InjectMocks
    BeersServiceImpl mockService;

    @Test
    public void getByIdShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Beers expectedBeer = createBeer();

        Mockito.when(repository.getById(anyInt()))
                .thenReturn(expectedBeer);

        //Act
        Beers returnedBeer = mockService.getById(INDEX_OF_ARRAYLIST_ELEMENT);

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

    @Test
    public void getByIdShould_TrowExceptionInRepository_WhenBeerDoesNotExist() {

        Mockito.when(repository.getById(anyInt()))
                .thenThrow(new EntityNotFoundException("Beer", anyInt()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getById(INDEX_OF_ARRAYLIST_ELEMENT));
    }

    @Test
    public void getBeersListShould_CallRepository() {
        //Arrange
        List<Beers> beerList = new ArrayList<>();

        Mockito.when(mockService.getBeersList())
                .thenReturn(beerList);
        //Act
        mockService.getBeersList();

        //Assert
        Assert.assertSame(mockService.getBeersList(), beerList);
    }

    @Test
    public void getSpecificBeerShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Beers expectedBeer = createBeer();

        Mockito.when(repository.getSpecificBeer(anyString()))
                .thenReturn(expectedBeer);

        //Act
        Beers returnedBeer = mockService.getSpecificBeer(anyString());

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

    @Test
    public void getSpecificBeerShould_TrowExceptionInRepository_WhenBeerDoesNotExist() {

        Mockito.when(repository.getSpecificBeer(anyString()))
                .thenThrow(new EntityNotFoundException("Beer", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> repository.getSpecificBeer("anyName"));
    }

    @Test
    public void getSpecificBeerShould_CallRepository() {
        //Arrange
        Beers expectedBeer = createBeer();

        Mockito.when(mockService.getSpecificBeer(NAME_OF_BEER))
                .thenReturn(expectedBeer);
        //Act
        mockService.getSpecificBeer(NAME_OF_BEER);

        //Assert
        Assert.assertSame(mockService.getSpecificBeer(NAME_OF_BEER), expectedBeer);
    }

    @Test
    public void createBeerShould_CreateBeer_WhenBeerDoesNotExist() {
        //Arrange
        Beers expectedBeer = createBeer();
        mockService.createBeer(expectedBeer);
        List<Beers> beerList = new ArrayList<>();
        beerList.add(expectedBeer);

        //Act
        Beers returnedBeer = beerList.get(INDEX_OF_ARRAYLIST_ELEMENT);

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

}