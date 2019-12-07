package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Tag;
import com.beertag.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.models.Constants.*;

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
    public Tag getSpecificTag(String name) {
        try {
            return tagRepository.getSpecificTag(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public void update(int id, Tag tag) {
        try {
            tagRepository.update(id, tag);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_ID_NOT_FOUND, id);
        }
    }

    @Override
    public void createTag(Tag tag) {
        try {
            tagRepository.createTag(tag);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_NAME_NOT_FOUND, tag.getName());
        }
    }

    @Override
    public void deleteTag(String name) {
        try {
            tagRepository.deleteTag(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_NAME_NOT_FOUND, name);
        }
    }
}
