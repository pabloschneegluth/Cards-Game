package com.drpicox.game.card;

import org.springframework.stereotype.Service;

@Service
public class CardPositionService {

    private CardRepository cardRepository;
    private CardService cardService;

    public CardPositionService(CardRepository cardRepository, CardService cardService) {
        this.cardRepository = cardRepository;
        this.cardService = cardService;
    }

    public Stack getStackByPosition(int position) {
        var cards = cardRepository.findAllByPositionOrderByZindexAsc(position);
        var stack = new Stack(position, cards);
        return stack;
    }

    public void moveCard(Card card, int targetPosition) {
        moveCard(card, targetPosition, 0);
    }

    public void moveCard(Card fromCard, Card toCard) {
        var position = toCard.getPosition();
        var zindex = toCard.getZindex() + 1;
        moveCard(fromCard, position, zindex);
    }

    public void moveCard(Card card, int targetPosition, int zindex) {
        var targetStack = getStackByPosition(targetPosition);
        var affectedCards = targetStack.insert(card, zindex);

        cardRepository.saveAll(affectedCards);
    }

    public int getMaxPosition() {
        var cards = cardRepository.findAllByOrderByPositionAsc();
        if (cards.isEmpty()) return -1;

        var last = cards.get(cards.size() - 1);
        return last.getPosition();
    }

    public int getCardFreePosition() {
        var freePosition = 0;
        while (cardRepository.existsByPosition(freePosition)) {
            freePosition++;
        }

        return freePosition;
    }
}
