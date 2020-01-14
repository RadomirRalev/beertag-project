package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.UserRepository;
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
import static com.beertag.demo.services.Factory.userUpdateDTO;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl mockService;

    @Test
    public void getByIdShould_ReturnUser_WhenBeerExists() {
        //Arrange
        User expectedUser = createUser();

        Mockito.when(repository.getById(anyInt()))
                .thenReturn(expectedUser);

        //Act
        User returnedBeer = mockService.getById(INDEX);

        //Assert
        Assert.assertSame(expectedUser, returnedBeer);
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
    public void getUsersListShould_CallRepository() {
        //Arrange
        List<User> userList = new ArrayList<>();

        Mockito.when(repository.getUsers())
                .thenReturn(userList);
        //Act
        mockService.getUsers();

        //Assert
        Assert.assertSame(mockService.getUsers(), userList);
    }

    @Test
    public void getSpecificUserShould_TrowException_WhenBeerDoesNotExist() {

        Mockito.when(repository.getByUsername(anyString()))
                .thenThrow(new EntityNotFoundException("Beer", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getByUsername("anyName"));
    }


    @Test
    public void getSpecificUserShould_CallRepository() {
        //Arrange
        User expectedUser = createUser();

        Mockito.when(repository.getByUsername(NAME))
                .thenReturn(expectedUser);
        //Act
        mockService.getByUsername(NAME);

        //Assert
        Assert.assertSame(mockService.getByUsername(NAME), expectedUser);
    }


    @Test
    public void createBeerShould_ThrowException_WhenBeerExists() {
        //Arrange
        doThrow(new RuntimeException()).when(repository).createUser(any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> repository.createUser(isA(User.class)));
    }

    @Test
    public void deleteBeerShould_ThrowException_WhenBeerDoesNotExist() {

        doThrow(new RuntimeException()).when(repository).setStatusUser(anyString(), anyInt());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> repository.setStatusUser(anyString(),anyInt()));
    }


    @Test
    public void updateShould_ThrowException_WhenBeerDoesNotExist() {

        User user = createUser();
        doThrow(new RuntimeException()).when(repository).updateUser(user);

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> repository.updateUser(user));
    }
}
