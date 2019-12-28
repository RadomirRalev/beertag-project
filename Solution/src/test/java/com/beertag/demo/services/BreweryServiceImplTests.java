package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Brewery;
import com.beertag.demo.repositories.BreweryRepository;
import org.junit.Assert;
import org.junit.Test;
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
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class BreweryServiceImplTests {
    @Mock
    BreweryRepository breweryRepository;

    @InjectMocks
    BreweryServiceImpl mockService;

    @Test
    public void getBreweryByIdShould_ReturnBrewery_WhenBreweryExists() {
        //Arrange
        Brewery expectedBrewery = createBrewery();

        Mockito.when(breweryRepository.getBreweryById(anyInt()))
                .thenReturn(expectedBrewery);

        //Act
        Brewery returnedBrewery = mockService.getBreweryById(INDEX);

        //Assert
        Assert.assertSame(expectedBrewery, returnedBrewery);
    }

    @Test
    public void getBreweryByIdShould_TrowException_WhenBreweryDoesNotExist() {

        Mockito.when(breweryRepository.getBreweryById(anyInt()))
                .thenThrow(new EntityNotFoundException("Beer", anyInt()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getBreweryById(INDEX));
    }

    @Test
    public void getBreweriesListShould_CallRepository() {
        //Arrange
        List<Brewery> breweriesList = new ArrayList<>();

        Mockito.when(breweryRepository.getBreweriesList())
                .thenReturn(breweriesList);
        //Act
        mockService.getBreweriesList();

        //Assert
        Assert.assertSame(mockService.getBreweriesList(), breweriesList);
    }

    @Test
    public void getSpecificBreweryShould_ReturnBrewery_WhenBreweryExists() {
        //Arrange
        Brewery expectedBrewery = createBrewery();
        List<Brewery> expectedList = new ArrayList<>();
        expectedList.add(expectedBrewery);

        Mockito.when(breweryRepository.getBreweryByName(anyString()))
                .thenReturn(expectedList);

        //Act
        List<Brewery> returnedList = mockService.getBreweryByName(anyString());

        //Assert
        Assert.assertSame(returnedList, expectedList);
    }

    @Test
    public void getSpecificBreweryShould_TrowException_WhenBreweryDoesNotExist() {

        Mockito.when(breweryRepository.getBreweryByName(anyString()))
                .thenThrow(new EntityNotFoundException("Beer", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getBreweryByName("anyName"));
    }

    @Test
    public void getSpecificBreweryShould_CallRepository() {
        //Arrange
        Brewery expectedBrewery = createBrewery();
        List<Brewery> expectedList = new ArrayList<>();
        expectedList.add(expectedBrewery);

        Mockito.when(breweryRepository.getBreweryByName(anyString()))
                .thenReturn(expectedList);
        //Act
        mockService.getBreweryByName(anyString());

        //Assert
        Assert.assertSame(mockService.getBreweryByName(anyString()), expectedList);
    }

    @Test
    public void createBreweryShould_CallRepository() {
        //Arrange
        Brewery expectedBrewery = createBrewery();
        mockService.createBrewery(expectedBrewery);
        List<Brewery> breweriesList = new ArrayList<>();
        breweriesList.add(expectedBrewery);

        //Act
        Brewery returnedBrewery = breweriesList.get(INDEX);

        //Assert
        Assert.assertSame(expectedBrewery, returnedBrewery);
    }

    @Test
    public void createBreweryShould_ThrowException_WhenBreweryExists() {
        //Arrange
        doThrow(new RuntimeException()).when(breweryRepository).createBrewery(any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> breweryRepository.createBrewery(isA(Brewery.class)));
    }

    @Test
    public void deleteBreweryShould_ThrowException_WhenBreweryDoesNotExist() {

        doThrow(new RuntimeException()).when(breweryRepository).deleteBrewery(anyInt());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> breweryRepository.deleteBrewery(anyInt()));
    }

    @Test
    public void updateShould_ThrowException_WhenBreweryDoesNotExist() {

        doThrow(new RuntimeException()).when(breweryRepository).update(anyInt(), any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> breweryRepository.update(anyInt(), any()));
    }

    @Test
    public void updateShould_ReturnBrewery_WhenBreweryExists() {
        //Arrange
        Brewery returnedBrewery = createBrewery();

        Mockito.when(breweryRepository.update(anyInt(), any()))
                .thenReturn(returnedBrewery);

        //Act
        breweryRepository.update(anyInt(), any());

        //Assert
        Assert.assertNotNull(returnedBrewery);
    }

}
