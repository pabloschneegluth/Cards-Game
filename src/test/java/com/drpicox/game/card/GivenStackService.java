package com.drpicox.game.card;

import com.drpicox.game.util.Names;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drpicox.game.util.GivenAlgorithm.given;

@Service
public class GivenStackService {

    private final CardPositionService cardPositionService;
    private final StackService stackService;
    private final CardFactory cardFactory;

    public GivenStackService(CardPositionService cardPositionService, StackService stackService, CardFactory cardFactory) {
        this.cardPositionService = cardPositionService;
        this.stackService = stackService;
        this.cardFactory = cardFactory;
    }


    public List<Stack> givenStacks(int count, Names names) {
        return given(count,
            () -> stackService.findAllStack(names),
            card -> stackService.discardStack(card),
            () -> createStack(names)
        );
    }

    public void givenStackAt(int position, Names names) {
        stackService.discardStack(position);
        createStack(names, new CardFactorySettings().withPosition(position));
    }

    private Stack createStack(Names names) {
        var freePosition = cardPositionService.getCardFreePosition();
        var settings = new CardFactorySettings().withPosition(freePosition);
        return createStack(names, settings);
    }

    private Stack createStack(Names names, CardFactorySettings settings) {
        for (var cardName: names) {
            cardFactory.makeCard(settings.withCardName(cardName));
        }
        return cardPositionService.getStackByPosition(settings.getPosition());
    }
}
