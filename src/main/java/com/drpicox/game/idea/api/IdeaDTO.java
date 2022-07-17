package com.drpicox.game.idea.api;

import com.drpicox.game.idea.Idea;
import com.drpicox.game.util.HasName;

import java.util.List;
import java.util.Optional;

import static com.drpicox.game.util.Names.byName;

public class IdeaDTO implements HasName {

    public IdeaDTO(Idea idea) {
        this.id = idea.getId();
        this.name = idea.getName();
        this.level = idea.getLevel();
        this.xp = idea.getXp();
        this.cardRewards = idea.getCardRewards().stream().map(r -> r.getCardName()).toList();
        this.tagRequirements = idea.getTagRequirements().stream().map(IdeaTagRequirementDTO::new).toList();
    }

    private String id;
    private String name;
    private List<IdeaTagRequirementDTO> tagRequirements;
    private int level;
    private int xp;
    private List<String> cardRewards;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public List<String> getCardRewards() {
        return cardRewards;
    }

    public Optional<IdeaTagRequirementDTO> findTagRequirement(String cardName) {
        var requirement = tagRequirements.stream().filter(byName(cardName)).findAny();
        return requirement;
    }

    @Override
    public String toString() {
        return "I-" + id + "-{" + level + "+" + xp + "}";
    }
}
