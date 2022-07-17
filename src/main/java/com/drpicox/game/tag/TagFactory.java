package com.drpicox.game.tag;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagFactory {

    private final TagRepository tagRepository;

    public TagFactory(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> makeAllTags(TagFactorySettings settings) {
        var cardConstants = settings.getCardConstants();
        var cardId = settings.getCardId();

        var tags = new ArrayList<Tag>();
        var tagTable = cardConstants.getCsvTable("tags");

        for (var tagRow: tagTable.getRows()) {
            var tagName = tagRow.get("tagName");
            var tagValue = tagRow.getInt("tagValue");

            var tag = new Tag(cardId, tagName, tagValue);
            tagRepository.save(tag);
            tags.add(tag);
        }

        return tags;
    }
}
