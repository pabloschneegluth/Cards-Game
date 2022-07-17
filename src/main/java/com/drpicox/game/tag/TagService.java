package com.drpicox.game.tag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAllByTagName(String tagName) {
        var tags = tagRepository.findAllByTagName(tagName);
        return tags;
    }
}
