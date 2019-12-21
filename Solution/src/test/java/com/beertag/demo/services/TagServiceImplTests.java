package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Tag;
import com.beertag.demo.repositories.TagRepository;
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
public class TagServiceImplTests {
    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagServiceImpl mockService;

    @Test
    public void getBreweryByIdShould_ReturnBeer_WhenBeerExists() {
        //Arrange
        Tag expectedTag = creatTag();

        Mockito.when(tagRepository.getTagById(anyInt()))
                .thenReturn(expectedTag);

        //Act
        Tag returnedTag = mockService.getTagById(INDEX);

        //Assert
        Assert.assertSame(expectedTag, returnedTag);
    }

    @Test
    public void getBreweryByIdShould_TrowException_WhenBeerDoesNotExist() {

        Mockito.when(tagRepository.getTagById(anyInt()))
                .thenThrow(new EntityNotFoundException("Beer", anyInt()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getTagById(INDEX));
    }

    @Test
    public void getBreweriesListShould_CallRepository() {
        //Arrange
        List<Tag> TagList = new ArrayList<>();

        Mockito.when(tagRepository.getTagList())
                .thenReturn(TagList);
        //Act
        mockService.getTagList();

        //Assert
        Assert.assertSame(mockService.getTagList(), TagList);
    }

    @Test
    public void getSpecificBreweryShould_ReturnBrewery_WhenBreweryExists() {
        //Arrange
        Tag expectedTag = creatTag();

        Mockito.when(tagRepository.getSpecificTag(anyString()))
                .thenReturn(expectedTag);

        //Act
        Tag returnedTag = mockService.getSpecificTag(anyString());

        //Assert
        Assert.assertSame(expectedTag, returnedTag);
    }

    @Test
    public void getSpecificBreweryShould_TrowException_WhenBreweryDoesNotExist() {

        Mockito.when(tagRepository.getSpecificTag(anyString()))
                .thenThrow(new EntityNotFoundException("Beer", anyString()));

        //Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> mockService.getSpecificTag("anyName"));
    }

    @Test
    public void getSpecificBreweryShould_CallRepository() {
        //Arrange
        Tag expectedTag = creatTag();

        Mockito.when(tagRepository.getSpecificTag(NAME))
                .thenReturn(expectedTag);
        //Act
        mockService.getSpecificTag(NAME);

        //Assert
        Assert.assertSame(mockService.getSpecificTag(NAME), expectedTag);
    }

    @Test
    public void createBreweryShould_CallRepository() {
        //Arrange
        Tag expectedTag = creatTag();
        mockService.createTag(expectedTag);
        List<Tag> tagList = new ArrayList<>();
        tagList.add(expectedTag);

        //Act
        Tag returnedTag = tagList.get(anyInt());

        //Assert
        Assert.assertSame(expectedTag, returnedTag);
    }

    @Test
    public void createBreweryShould_ThrowException_WhenBreweryExists() {
        //Arrange
        doThrow(new RuntimeException()).when(tagRepository).createTag(any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> tagRepository.createTag(isA(Tag.class)));
    }

    @Test
    public void deleteBreweryShould_ThrowException_WhenBreweryDoesNotExist() {

        doThrow(new RuntimeException()).when(tagRepository).deleteTag(anyString());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> tagRepository.deleteTag(anyString()));
    }

    @Test
    public void updateShould_ThrowException_WhenBreweryDoesNotExist() {

        doThrow(new RuntimeException()).when(tagRepository).update(anyInt(), any());

        //Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> tagRepository.update(anyInt(), any()));
    }

    @Test
    public void updateShould_ReturnBrewery_WhenBreweryExists() {
        //Arrange
        Tag returnedTag = creatTag();

        Mockito.when(tagRepository.update(anyInt(),any()))
                .thenReturn(returnedTag);

        //Act
        tagRepository.update(anyInt(), any());

        //Assert
        Assert.assertNotNull(returnedTag);
    }

}