package com.drpicox.game.card;

import com.drpicox.game.tag.Tag;
import com.drpicox.game.util.HasName;
import com.drpicox.game.util.HasPosition;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;

@Entity
public class Card implements HasName, HasPosition {
    @Id private String id;
    private String name;
    private int position;
    private int zindex;
    private int maxProgress;
    private int progress;
    private String looksLike;

    @ElementCollection private Map<String,String> description;

    @OneToMany
    private List<Tag> tags;

    public Card(String id, String name, Map<String,String> description, List<Tag> tags, int position, int zindex, String looksLike) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.position = position;
        this.zindex = zindex;
        this.maxProgress = 1;
        this.progress = 0;
        this.looksLike = looksLike;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public int getPosition() {
        return position;
    }

    public int getZindex() {
        return zindex;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public String getLooksLike() {
        return looksLike;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public String getDescriptionTerm(String term) {
        return description.get(term);
    }

    public int getTagValue(String tagName) {
        return tags.stream()
            .filter(tag -> tag.getName().equals(tagName))
            .mapToInt(tag -> tag.getValue())
            .findAny()
            .orElse(0);
    }


    void placeAt(int position, int zindex) {
        this.position = position;
        this.zindex = zindex;
    }

    int progress(int maxProgress) {
        this.maxProgress = maxProgress;
        progress += 1;

        if (progress >= maxProgress) {
            progress = 0;
        }

        return progress;
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Card) obj;
        return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "C-" + id + "[#" + position + " z" + zindex + "]";
    }

    protected Card() {} // JPA required constructor
}
