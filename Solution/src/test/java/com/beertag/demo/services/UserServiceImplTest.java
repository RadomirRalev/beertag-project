package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl mockService;

    @Test
    public void createUserShould_ThrowException_WhenUserExist() {
        //Arrange
        Mockito.when(mockService.usernameExist(anyString())).thenReturn(true);

        //Assert
        Assertions.assertThrows(DuplicateEntityException.class,
                () -> mockService.createUser(createUserRegistration()));
    }

    @Test
    public void createUserShould_ThrowException_WhenEmailExist() {
        //Arrange
        Mockito.when(mockService.emailExist(anyString())).thenReturn(true);

        //Assert
        Assertions.assertThrows(DuplicateEntityException.class,
                () -> mockService.createUser(createUserRegistration()));
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
    public void updateUserShould_ThrowException_whenNameNotExist() {
        //Arrange
        Mockito.when(mockService.usernameExist(anyString())).thenReturn(false);

        //Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.updateUser(createUser(),userUpdateDTO()));
    }
    @Test
    public void deleteUserShould_ThrowException_whenNameNotExist() {
        //Arrange
        Mockito.when(mockService.usernameExist(anyString())).thenReturn(false);

        //Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.updateUser(createUser(),userUpdateDTO()));
    }
}
