package com.beertag.demo.services;

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

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceImplTests {
    @Mock
    BeersRepository repository;

    @InjectMocks
    BeersServiceImpl mockService;

    @Test
    public void getByIdShould_ReturnBeer_WhenBeerExists() {
        //Arrange

        Mockito.when(repository.getById(0))
                .thenReturn(new Beers("Zagorka", "kkkk",
                        "okok", "2.14", "pop", "tag"));
        //Act
        Beers returnedBeer = mockService.getById(0);

        //Assert
        Assert.assertSame(returnedBeer.getId(), 0);
    }

    @Test
    public void getByIdShould_TrowException_WhenBeerDoesNotExist() {
        //Arrange
        Mockito.when(repository.getById(10))
                .thenThrow(new EntityNotFoundException("Beer", 10));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> repository.getById(10));
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
}