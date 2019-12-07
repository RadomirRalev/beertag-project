package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Tag;

import java.util.List;

public interface TagRepository {

    Tag getTagById(int id);

    List<Tag> getTagList();

    void update(int id, Tag name);

    void createTag(Tag newTag);

    boolean checkTagExists(String name);

    void deleteTag(String name);

    Tag getSpecificTag(String name);


}
