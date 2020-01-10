package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Tag;

import java.util.List;
import java.util.Set;

public interface TagRepository {

    Tag getTagById(int id);

    List<Tag> getTagList();

    Tag update(int id, Tag name);

    Tag createTag(Tag newTag);

    boolean checkTagExists(String name);

    void deleteTag(int id);

    List<Tag> getTagByName(String name);

    void addTagToBeer(int tagId, int beerId);

    void removeTagFromBeer(int tagId, int beerId);
}
