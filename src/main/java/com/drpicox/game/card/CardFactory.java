package com.drpicox.game.card;

import com.drpicox.game.tag.TagFactory;
import com.drpicox.game.tag.TagFactorySettings;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class CardFactory {

    private final CardService cardService;
    private final CardRepository cardRepository;
    private final CardPositionService cardPositionService;
    private final TagFactory tagFactory;
    private final CardConstantsCollection cardConstantsCollection;

    public CardFactory(CardService cardService, CardRepository cardRepository, CardPositionService cardPositionService, TagFactory tagFactory, CardConstantsCollection cardConstantsCollection) {
        this.cardService = cardService;
        this.cardRepository = cardRepository;
        this.cardPositionService = cardPositionService;
        this.tagFactory = tagFactory;
        this.cardConstantsCollection = cardConstantsCollection;
    }

    public void makeCards(int count, CardFactorySettings settings) {
        for (int i = 0; i < count; i++) {
            makeCard(settings);
        }
    }

    public Card makeCard(CardFactorySettings settings) {
        var cardName = settings.getCardName();

        var cardConstants = cardConstantsCollection.getByCardName(cardName);
        settings.withCardConstants(cardConstants);

        var cardId = getNextId(settings);

        var tags = tagFactory.makeAllTags(new TagFactorySettings(cardId).withCardConstants(cardConstants));
        var position = settings.getPosition();
        var zindex = settings.hasZindex()
            ? settings.getZindex()
            : cardPositionService.getStackByPosition(position).getMaxZindex() + 1;
        var description = getCardDescription(settings);
        var looksLike = cardConstants.getString("looksLike");

        var card = new Card(cardId, cardName, description, tags, position, zindex, looksLike);
        cardRepository.save(card);
        return card;
    }

    public void replaceCard(Card cardToDiscard, String newCardName) {
        var position = cardToDiscard.getPosition();
        var zindex = cardToDiscard.getZindex();

        cardService.discardCard(cardToDiscard);
        makeCard(new CardFactorySettings("Corpse").withPosition(position).withZindex(zindex));
    }

    private Map<String, String> getCardDescription(CardFactorySettings settings) {
        var description = new TreeMap<String, String>();
        var cardConstants = settings.getCardConstants();

        var descriptionTable = cardConstants.getCsvTable("description");
        for (var descriptionRow: descriptionTable.getRows()) {
            var term = descriptionRow.get("term");
            var text = descriptionRow.get("text");
            description.put(term, text);
        }

        return description;
    }

    private final String getNextId(CardFactorySettings settings) {
        var cardName = settings.getCardName();
        var kebabCardName = cardName.replaceAll("[^\\w]", "-").toLowerCase();
        var allCards = cardRepository.findAllByName(cardName);
        var maxId = allCards.stream()
            .map(c -> c.getId())
            .map(i -> i.substring(kebabCardName.length() + 1))
            .map(i -> Integer.parseInt(i))
            .max(Integer::compare)
            .orElse(0);
        return kebabCardName + "-" + (maxId + 1);
    }
}
