package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Style;
import com.beertag.demo.repositories.StyleRepository;
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
public class StyleServiceImplTests {
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

        Mockito.when(StyleRepository.getSpecificStyle(anyString()))
                .thenReturn(expectedStyle);

        //Act
        Style returnedStyle = mockService.getSpecificStyle(anyString());

        //Assert
        Assert.assertSame(expectedStyle, returnedStyle);
    }

    @Test
    public void getSpecificStyleShould_TrowException_WhenStyleDoesNotExist() {

        Mockito.when(StyleRepository.getSpecificStyle(anyString()))
                .thenThrow(new EntityNotFoundException("Style", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getSpecificStyle("anyName"));
    }

    @Test
    public void getSpecificStyleShould_CallRepository() {
        //Arrange
        Style expectedStyle = createStyle();

        Mockito.when(StyleRepository.getSpecificStyle(NAME))
                .thenReturn(expectedStyle);
        //Act
        mockService.getSpecificStyle(NAME);

        //Assert
        Assert.assertSame(mockService.getSpecificStyle(NAME), expectedStyle);
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

        doThrow(new RuntimeException()).when(StyleRepository).deleteStyle(anyString());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> StyleRepository.deleteStyle(anyString()));
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
