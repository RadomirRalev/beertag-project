package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.UserRepository;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.beertag.demo.services.Factory.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    //TODO
//    private static ValidatorFactory validatorFactory;
//    private static Validator validator;
//
//    @BeforeClass
//    public static void createValidator() {
//        validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//
//    @AfterClass
//    public static void close() {
//        validatorFactory.close();
//    }

    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl mockService;

//    @Test
//    public void createUserShould_ThrowException_WhenUserExist() {
//        //Arrange
//        Mockito.when(mockService.userExist(anyString())).thenReturn(true);
//
//        //Assert
//        Assertions.assertThrows(DuplicateEntityException.class,
//                () -> mockService.createUser(createUser()));
//    }
//
//    @Test
//    public void createUserShould_ThrowException_WhenEmailExist() {
//        //Arrange
//        Mockito.when(mockService.emailExist(anyString())).thenReturn(true);
//
//        //Assert
//        Assertions.assertThrows(DuplicateEntityException.class,
//                () -> mockService.createUser(createUser()));
//    }
    //TODO
//    @Test
//    public void createUserShould_ThrowException_WhenUserNameHaveInvalidSymbols() {
//        //Arrange
//
//        User user = mockService.createUser(createUserWith3SymbolsForName());
//
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//        Assertions.assertNotNull(violations);
//
//    }

//    @Test
//    public void getUsersListShould_CallRepository() {
//        //Arrange
//        List<User> userList = new ArrayList<>();
//
//        Mockito.when(repository.showUsers())
//                .thenReturn(userList);
//        //Act
//        mockService.showUsers();
//
//        //Assert
//        Assert.assertSame(mockService.showUsers(), userList);
//    }
//
//    @Test
//    public void updateUserShould_ThrowException_whenNameNotExist() {
//        //Arrange
//        Mockito.when(mockService.userExist(anyString())).thenReturn(false);
//
//        //Assert
//        Assertions.assertThrows(EntityNotFoundException.class,
//                () -> mockService.updateUser(createUser()));
//    }
//    @Test
//    public void deleteUserShould_ThrowException_whenNameNotExist() {
//        //Arrange
//        Mockito.when(mockService.userExist(anyString())).thenReturn(false);
//
//        //Assert
//        Assertions.assertThrows(EntityNotFoundException.class,
//                () -> mockService.updateUser(createUser()));
//    }
}
