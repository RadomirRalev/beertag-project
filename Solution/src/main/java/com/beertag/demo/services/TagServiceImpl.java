package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Tag;
import com.beertag.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

@Service
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagById(int id) {
        try {
            return tagRepository.getTagById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_ID_NOT_FOUND, id);
        }
    }

    @Override
    public List<Tag> getTagList() {
        try {
            return tagRepository.getTagList();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public List<Tag> getTagByName(String name) {
        try {
            return tagRepository.getTagByName(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Tag update(int id, Tag tag) {
        try {
            tagRepository.update(id, tag);
            return tag;
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_ID_NOT_FOUND, id);
        }
    }

    @Override
    public Tag createTag(Tag newTag) {
        newTag.setStatus(ENABLE);
        try {
            return tagRepository.createTag(newTag);
        } catch (Exception e) {
            throw new DuplicateEntityException(STYLE_NAME_EXISTS, newTag.getName());
        }
    }

    @Override
    public void deleteTag(int id) {
          tagRepository.deleteTag(id);
    }

    @Override
    public void addTagToBeer(int tagId, int beerId) {
        tagRepository.addTagToBeer(tagId, beerId);
    }

    @Override
    public void removeTagFromBeer(int tagId, int beerId) {
        tagRepository.removeTagFromBeer(tagId, beerId);
    }
}
