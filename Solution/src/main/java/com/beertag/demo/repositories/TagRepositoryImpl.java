package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        return tagList;
    }

    @Override
    public void update(int id, Tag updateTag) {
        Tag currentTag = getTagById(id);
        currentTag.setName(updateTag.getName());
    }

    @Override
    public void createTag(Tag newTag) {
        newTag.setId(tagID++);
        tagList.add(newTag);
    }

    @Override
    public boolean checkTagExists(String name) {
        return tagList.stream()
                .anyMatch(tag -> tag.getName().equals(name));
    }

    @Override
    public void deleteTag(String name) {
        Tag tagToBeRemoved = getByString(name);
        tagList.remove(tagToBeRemoved);
    }

    @Override
    public Tag getSpecificTag(String name) {
        return getByString(name);
    }

    private Tag getByString(String name) {
        return tagList.stream()
                .filter(tag -> tag.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Tag %s not found in the database", name));
    }
}
