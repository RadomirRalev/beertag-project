package com.beertag.demo.services;

import com.beertag.demo.models.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(int id);

    List<Tag> getTagList();

    Tag getSpecificTag(String name);

    void update(int id, Tag tag);

    void createTag(Tag tag);

    void deleteTag(String name);
}
