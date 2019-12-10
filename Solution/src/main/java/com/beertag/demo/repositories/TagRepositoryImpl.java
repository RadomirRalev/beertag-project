package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Repository
public class TagRepositoryImpl implements TagRepository {
    private static int tagID;
    private List<Tag> tagList;

    public TagRepositoryImpl() {
        tagList = new ArrayList<>();
        Tag tag = new Tag("Tag1");
        tag.setId(tagID++);
        tagList.add(tag);
        tag = new Tag("Tag2");
        tag.setId(tagID++);
        tagList.add(tag);
        tag = new Tag("Tag3");
        tag.setId(tagID++);
        tagList.add(tag);
    }

    @Override
    public Tag getTagById(int id) {
        return tagList.stream()
                .filter(tag -> tag.getId() == id)
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException("Tag", id));
    }

    @Override
    public List<Tag> getTagList() {
        try {
            return tagList;
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Tag update(int id, Tag updateTag) {
        try {
            Tag currentTag = getTagById(id);
            currentTag.setName(updateTag.getName());
            return currentTag;
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_ID_NOT_FOUND, id);
        }
    }

    @Override
    public Tag createTag(Tag newTag) {
        try {
            newTag.setId(tagID++);
            tagList.add(newTag);
            return newTag;
        } catch (Exception e) {
            throw new DuplicateEntityException(TAG_NAME_EXISTS, newTag.getName());
        }
    }

    @Override
    public boolean checkTagExists(String name) {
        return tagList.stream()
                .anyMatch(tag -> tag.getName().equals(name));
    }

    @Override
    public void deleteTag(String name) {
        try {
            Tag tagToBeRemoved = getByString(name);
            tagList.remove(tagToBeRemoved);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Tag getSpecificTag(String name) {
        return getByString(name);
    }

    private Tag getByString(String name) {
        return tagList.stream()
                .filter(tag -> tag.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(TAG_NAME_NOT_FOUND, name));
    }
}
