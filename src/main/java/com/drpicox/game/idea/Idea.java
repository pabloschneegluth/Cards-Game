package com.drpicox.game.idea;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.Stack;
import com.drpicox.game.util.HasName;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Idea implements HasName {
    public Idea(String id, String name, int level, int xp, List<IdeaTagRequirement> requirements, List<IdeaCardReward> cardRewards) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.requirements = requirements;
        this.cardRewards = cardRewards;
    }

    @Id private String id;
    private String name;
    private int level;
    private int xp;
    @ElementCollection private List<IdeaTagRequirement> requirements;
    @ElementCollection private List<IdeaCardReward> cardRewards;

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public List<IdeaTagRequirement> getTagRequirements() {
        return requirements;
    }

    public int countRequiredCards() {
        return requirements.stream().mapToInt(r -> r.getCardCount()).sum();
    }

    public List<IdeaCardReward> getCardRewards() {
        return cardRewards.stream().filter(r -> r.getLevel() <= level).toList();
    }

    void increaseXp() {
        this.xp += 1;
        if (xp >= level * 10) {
            level += 1;
            xp = 0;
        }
    }

    protected Idea() {} // JPA required constructor

    @Override
    public String toString() {
        return "I-" + id + "-{" + level + "+" + xp + "}";
    }
}
