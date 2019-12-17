package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.helpers.BeerCollectionHelper;
import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Style;
import com.beertag.demo.services.BeerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.services.Factory.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class BeerControllerTests {
    @Mock
    BeerService mockService;
    DtoMapper mockMapper;

    @InjectMocks
    BeerController beerController;

    @Test
    public void getByIdShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Beer expectedBeer = createBeer();

        Mockito.when(mockService.getById(anyInt()))
                .thenReturn(expectedBeer);

        //Act
        Beer returnedBeer = beerController.getById(INDEX);

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

    @Test
    public void getByIdShould_TrowException_WhenBeerDoesNotExist() {

        Mockito.when(mockService.getById(anyInt()))
                .thenThrow(new EntityNotFoundException("Beer", anyInt()));

        //Act & Assert
        Assertions.assertThrows(ResponseStatusException.class,
                () -> beerController.getById(INDEX));
    }

    @Test
    public void getBeersListShould_CallService() {
        //Arrange
        List<Beer> beerList = new ArrayList<>();

        Mockito.when(mockService.getBeersList())
                .thenReturn(beerList);
        //Act
        beerController.getBeersList(null, null, null);

        //Assert
        Assert.assertSame(beerController.getBeersList(null, null, null), beerList);
    }

    @Test
    public void getSpecificBeerShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Beer expectedBeer = createBeer();

        Mockito.when(mockService.getSpecificBeer(anyString()))
                .thenReturn(expectedBeer);

        //Act
        Beer returnedBeer = beerController.getSpecificBeer(anyString());

        //Assert
        Assert.assertSame(expectedBeer, returnedBeer);
    }

    @Test
    public void getBeerList_should_filter_by_country() {
        //Arrange
        Beer expectedBeer1 = createBeer();
        Beer expectedBeer2 = createBeer2();
        List<Beer> beerList = new ArrayList<>();
        beerList.add(expectedBeer1);
        beerList.add(expectedBeer2);
        Mockito.when(beerController.getBeersList(expectedBeer1.getName(), null, null))
                .thenReturn(BeerCollectionHelper.filterByName(beerList, expectedBeer1.getName()));

        //Act
        beerList = beerController.getBeersList(expectedBeer1.getName(), null, null);
        //Assert
        Assert.assertEquals(beerList.size(), 1);
        Assert.assertEquals(expectedBeer1.getName(), beerList.get(0).getName());
    }
}
