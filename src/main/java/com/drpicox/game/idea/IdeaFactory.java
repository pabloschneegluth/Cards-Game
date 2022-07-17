package com.drpicox.game.idea;

import com.drpicox.game.card.CardConstantsCollection;
import com.drpicox.game.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaFactory {

    private final IdeaRepository ideaRepository;
    private final CardConstantsCollection cardConstantsCollection;

    public IdeaFactory(IdeaRepository ideaRepository, CardConstantsCollection cardConstantsCollection) {
        this.ideaRepository = ideaRepository;
        this.cardConstantsCollection = cardConstantsCollection;
    }

    public void makeIdea(IdeaFactorySettings ideaFactorySettings) {
        var ideaName = ideaFactorySettings.getIdeaName();
        var id = getIdFromName(ideaName);
        if (ideaRepository.existsById(id)) return;

        var ideaConstants = cardConstantsCollection.getByCardName(ideaName);
        var requirements = getCardRequirements(ideaConstants);
        var cardRewards = getCardRewards(ideaConstants);
        var level = ideaFactorySettings.getLevel();
        var xp = ideaFactorySettings.getXp();
        var idea = new Idea(id, ideaName, level, xp, requirements, cardRewards);
        ideaRepository.save(idea);
    }

    static String getIdFromName(String ideaName) {
        return ideaName.toLowerCase().replace(" ", "-");
    }

    private List<IdeaTagRequirement> getCardRequirements(Constants ideaConstants) {
        var requirements = new ArrayList<IdeaTagRequirement>();

        var requirementsTable = ideaConstants.getCsvTable("idea.requirement.tags");
        for (var requirementRow: requirementsTable.getRows()) {
            var cardCount = requirementRow.getInt("cardCount");
            var tagValue = requirementRow.getInt("tagValue");
            var tagName = requirementRow.get("tagName");

            var requirement = new IdeaTagRequirement(cardCount, tagValue, tagName);
            requirements.add(requirement);
        }

        return requirements;
    }

    private List<IdeaCardReward> getCardRewards(Constants ideaConstants) {
        var cardRewards = new ArrayList<IdeaCardReward>();

        var cardRewardsTable = ideaConstants.getCsvTable("idea.rewards.cards");
        for (var row: cardRewardsTable.getRows()) {
            var level = row.getInt("level");
            var cardName = row.get("cardName");
            var possibilities = row.getInt("possibilities");

            var cardReward = new IdeaCardReward(cardName, level, possibilities);
            cardRewards.add(cardReward);
        }

        return cardRewards;
    }
}
