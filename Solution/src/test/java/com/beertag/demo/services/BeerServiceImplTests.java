package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.repositories.BeerRepository;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceImplTests {
    @Mock
    BeerRepository repository;

    @InjectMocks
    BeerServiceImpl mockService;

    @Test
    public void getByIdShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Beer expectedBeer = createBeer();

            Mockito.when(repository.getById(anyInt()))
                    .thenReturn(expectedBeer);

        //Act
        Beer returnedBeer = mockService.getById(INDEX);

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

    @Test
    public void getByIdShould_ThrowException_WhenBeerDoesNotExist() {

        Mockito.when(repository.getById(anyInt()))
                .thenThrow(new EntityNotFoundException("Beer", anyInt()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getById(INDEX));
    }

    @Test
    public void getBeersListShould_CallRepository() {
        //Arrange
        List<Beer> beerList = new ArrayList<>();

        Mockito.when(repository.getBeerList())
                .thenReturn(beerList);
        //Act
        mockService.getBeersList();

        //Assert
        Assert.assertSame(mockService.getBeersList(), beerList);
    }

    @Test
    public void getSpecificBeerShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Beer expectedBeer = createBeer();
        List<Beer> expectedList = new ArrayList<>();
        expectedList.add(expectedBeer);

        Mockito.when(repository.getBeerByName(anyString()))
                .thenReturn(expectedList);

        //Act
        List<Beer> returnedList = mockService.getBeerByName(anyString());

        //Assert
        Assert.assertSame(expectedList, returnedList);
    }

    @Test
    public void getSpecificBeerShould_TrowException_WhenBeerDoesNotExist() {

        Mockito.when(repository.getBeerByName(anyString()))
                .thenThrow(new EntityNotFoundException("Beer", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getBeerByName("anyName"));
    }

    @Test
    public void getSpecificBeerShould_CallRepository() {
        //Arrange
        Beer expectedBeer = createBeer();
        List<Beer> expectedList = new ArrayList<>();
        expectedList.add(expectedBeer);

        Mockito.when(repository.getBeerByName(NAME))
                .thenReturn(expectedList);
        //Act
        mockService.getBeerByName(NAME);

        //Assert
        Assert.assertSame(mockService.getBeerByName(NAME), expectedList);
    }

    @Test
    public void createBeerShould_CallRepository() {
        //Arrange
        Beer expectedBeer = createBeer();
        mockService.createBeer(expectedBeer);
        List<Beer> beerList = new ArrayList<>();
        beerList.add(expectedBeer);

        //Act
        Beer returnedBeer = beerList.get(INDEX);

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

    @Test
    public void createBeerShould_ThrowException_WhenBeerExists() {
        //Arrange
        doThrow(new RuntimeException()).when(repository).createBeer(any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> repository.createBeer(isA(Beer.class)));
    }

    @Test
    public void deleteBeerShould_ThrowException_WhenBeerDoesNotExist() {

        doThrow(new RuntimeException()).when(repository).deleteBeer(anyInt());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> repository.deleteBeer(anyInt()));
    }

    @Test
    public void updateShould_ThrowException_WhenBeerDoesNotExist() {

        doThrow(new RuntimeException()).when(repository).update(anyInt(), any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> repository.update(anyInt(), any()));
    }
}