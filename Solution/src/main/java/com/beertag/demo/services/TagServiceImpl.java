package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.Tag;
import com.beertag.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagById(int id) {
        return tagRepository.getTagById(id);
    }

    @Override
    public List<Tag> getTagList() {
        return tagRepository.getTagList();
    }

    @Override
    public Tag getSpecificTag(String name) {
        return tagRepository.getSpecificTag(name);
    }

    @Override
    public void update(int id, Tag tag) {
        tagRepository.update(id, tag);
    }

    @Override
    public void createTag(Tag tag) {
        if (tagRepository.checkTagExists(tag.getName())) {
            throw new DuplicateEntityException("Tag", tag.getName());
        }
        tagRepository.createTag(tag);
    }

    @Override
    public void deleteTag(String name) {
        tagRepository.deleteTag(name);
    }
}
