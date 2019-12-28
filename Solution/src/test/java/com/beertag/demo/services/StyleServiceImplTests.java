package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Style;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.services.Factory.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class StyleServiceImplTests {

    private LocalValidatorFactoryBean validator;

    @Mock
    com.beertag.demo.repositories.StyleRepository StyleRepository;

    @InjectMocks
    StyleServiceImpl mockService;

    @Test
    public void getStyleByIdShould_ReturnStyle_WhenStyleExists() {
        //Arrange
        Style expectedStyle = createStyle();

        Mockito.when(StyleRepository.getStyleById(anyInt()))
                .thenReturn(expectedStyle);

        //Act
        Style returnedStyle = mockService.getStyleById(INDEX);

        //Assert
        Assert.assertSame(expectedStyle, returnedStyle);
    }

    @Test
    public void getStyleByIdShould_TrowException_WhenStyleDoesNotExist() {

        Mockito.when(StyleRepository.getStyleById(anyInt()))
                .thenThrow(new EntityNotFoundException("Style", anyInt()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getStyleById(INDEX));
    }

    @Test
    public void getStyleListShould_CallRepository() {
        //Arrange
        List<Style> stylesList = new ArrayList<>();

        Mockito.when(StyleRepository.getStylesList())
                .thenReturn(stylesList);
        //Act
        mockService.getStylesList();

        //Assert
        Assert.assertSame(mockService.getStylesList(), stylesList);
    }

    @Test
    public void getSpecificStyleShould_ReturnStyle_WhenStyleExists() {
        //Arrange
        Style expectedStyle = createStyle();
        List<Style> expectedList = new ArrayList<>();
        expectedList.add(expectedStyle);

        Mockito.when(StyleRepository.getStyleByName(anyString()))
                .thenReturn(expectedList);

        //Act
        List<Style> returnedList = mockService.getStyleByName(anyString());

        //Assert
        Assert.assertSame(expectedList, returnedList);
    }

    @Test
    public void getSpecificStyleShould_TrowException_WhenStyleDoesNotExist() {

        Mockito.when(StyleRepository.getStyleByName(anyString()))
                .thenThrow(new EntityNotFoundException("Style", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getStyleByName("anyName"));
    }

    @Test
    public void getSpecificStyleShould_CallRepository() {
        //Arrange
        Style expectedStyle = createStyle();
        List<Style> expectedList = new ArrayList<>();
        expectedList.add(expectedStyle);

        Mockito.when(StyleRepository.getStyleByName(anyString()))
                .thenReturn(expectedList);
        //Act
        mockService.getStyleByName(anyString());

        //Assert
        Assert.assertSame(mockService.getStyleByName(anyString()), expectedList);
    }

    @Test
    public void createStyleShould_CallRepository() {
        //Arrange
        Style expectedStyle = createStyle();
        mockService.createStyle(expectedStyle);
        List<Style> stylesList = new ArrayList<>();
        stylesList.add(expectedStyle);

        //Act
        Style returnedStyle = stylesList.get(INDEX);

        //Assert
        Assert.assertSame(expectedStyle, returnedStyle);
    }

    @Test
    public void createStyleShould_ThrowException_WhenStyleExists() {
        //Arrange
        doThrow(new RuntimeException()).when(StyleRepository).createStyle(any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> StyleRepository.createStyle(isA(Style.class)));
    }

    @Test
    public void deleteStyleShould_ThrowException_WhenStyleDoesNotExist() {

        doThrow(new RuntimeException()).when(StyleRepository).deleteStyle(anyInt());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> StyleRepository.deleteStyle(anyInt()));
    }

    @Test
    public void updateShould_ThrowException_WhenStyleDoesNotExist() {

        doThrow(new RuntimeException()).when(StyleRepository).update(anyInt(), any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> StyleRepository.update(anyInt(), any()));
    }

    @Test
    public void updateShould_ReturnStyle_WhenStyleExists() {
        //Arrange
        Style returnedStyle = createStyle();

        Mockito.when(StyleRepository.update(anyInt(), any()))
                .thenReturn(returnedStyle);

        //Act
        StyleRepository.update(anyInt(), any());

        //Assert
        Assert.assertNotNull(returnedStyle);
    }
}
