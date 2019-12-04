package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.repositories.BeersRepository;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceImplTests {
    @Mock
    BeersRepository repository;

    @InjectMocks
    BeersServiceImpl mockService;

    @Test
    public void getByIdShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        //BeersRepository repository = Mockito.mock(BeersRepository.class);
        //BeersService mockService = new BeersServiceImpl(repository);

        Mockito.when(repository.getById(0))
                .thenReturn(new Beers("Zagorka", "kkkk", "Bulgaria",
                        "okok", "2.14", "pop", "tag"));
        //Act
        Beers returnedBeer = mockService.getById(0);

        //Assert
        Assert.assertSame(returnedBeer.getId(), 0);
    }
}
