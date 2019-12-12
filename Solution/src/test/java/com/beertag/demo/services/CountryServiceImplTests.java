package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Country;
import com.beertag.demo.repositories.CountryRepository;
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

public class CountryServiceImplTests {
    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryServiceImpl mockService;

    @Test
    public void getCountryByIdShould_ReturnCountry_WhenCountryExists() {
        //Arrange
        Country expectedCountry = createCountry();

        Mockito.when(countryRepository.getCountryById(anyInt()))
                .thenReturn(expectedCountry);

        //Act
        Country returnedCountry = mockService.getCountryById(INDEX);

        //Assert
        Assert.assertSame(expectedCountry, returnedCountry);
    }

    @Test
    public void getCountryByIdShould_TrowException_WhenCountryDoesNotExist() {

        Mockito.when(countryRepository.getCountryById(anyInt()))
                .thenThrow(new EntityNotFoundException("Country", anyInt()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getCountryById(INDEX));
    }

    @Test
    public void getCountryListShould_CallRepository() {
        //Arrange
        List<Country> countriesList = new ArrayList<>();

        Mockito.when(countryRepository.getCountriesList())
                .thenReturn(countriesList);
        //Act
        mockService.getCountriesList();

        //Assert
        Assert.assertSame(mockService.getCountriesList(), countriesList);
    }

    @Test
    public void getSpecificCountryShould_ReturnCountry_WhenCountryExists() {
        //Arrange
        Country expectedCountry = createCountry();

        Mockito.when(countryRepository.getSpecificCountry(anyString()))
                .thenReturn(expectedCountry);

        //Act
        Country returnedCountry = mockService.getSpecificCountry(anyString());

        //Assert
        Assert.assertSame(expectedCountry, returnedCountry);
    }

    @Test
    public void getSpecificCountryShould_TrowException_WhenCountryDoesNotExist() {

        Mockito.when(countryRepository.getSpecificCountry(anyString()))
                .thenThrow(new EntityNotFoundException("Country", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getSpecificCountry("anyName"));
    }

    @Test
    public void getSpecificCountryShould_CallRepository() {
        //Arrange
        Country expectedCountry = createCountry();

        Mockito.when(countryRepository.getSpecificCountry(NAME))
                .thenReturn(expectedCountry);
        //Act
        mockService.getSpecificCountry(NAME);

        //Assert
        Assert.assertSame(mockService.getSpecificCountry(NAME), expectedCountry);
    }

    @Test
    public void createCountryShould_CallRepository() {
        //Arrange
        Country expectedCountry = createCountry();
        mockService.createCountry(expectedCountry);
        List<Country> countriesList = new ArrayList<>();
        countriesList.add(expectedCountry);

        //Act
        Country returnedCountry = countriesList.get(INDEX);

        //Assert
        Assert.assertSame(expectedCountry, returnedCountry);
    }

    @Test
    public void createCountryShould_ThrowException_WhenCountryExists() {
        //Arrange
        doThrow(new RuntimeException()).when(countryRepository).createCountry(any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> countryRepository.createCountry(isA(Country.class)));
    }

    @Test
    public void deleteCountryShould_ThrowException_WhenCountryDoesNotExist() {

        doThrow(new RuntimeException()).when(countryRepository).deleteCountry(anyString());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> countryRepository.deleteCountry(anyString()));
    }

    @Test
    public void updateShould_ThrowException_WhenCountryDoesNotExist() {

        doThrow(new RuntimeException()).when(countryRepository).update(anyInt(), any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> countryRepository.update(anyInt(), any()));
    }

    @Test
    public void updateShould_ReturnCountry_WhenCountryExists() {
        //Arrange
        Country returnedCountry = createCountry();

        Mockito.when(countryRepository.update(anyInt(), any()))
                .thenReturn(returnedCountry);

        //Act
        countryRepository.update(anyInt(), any());

        //Assert
        Assert.assertNotNull(returnedCountry);
    }

}
