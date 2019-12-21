package com.beertag.demo.services;

import com.beertag.demo.models.beer.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(int id);

    List<Tag> getTagList();

    Tag getSpecificTag(String name);

    Tag update(int id, Tag tag);

    Tag createTag(Tag tag);

    void deleteTag(String name);
}