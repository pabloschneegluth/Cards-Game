package com.drpicox.game.animal;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;
import com.drpicox.game.card.CardFactorySettings;

@Component
public class EndMoonStep_200_ChickenLaysEgg implements EndMoonStep {
    private final CardService cardService;
    private final CardFactory cardFactory;
    public EndMoonStep_200_ChickenLaysEgg(CardService cardService,CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory=cardFactory;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        var chickens = cardService.findAllByName("Chicken");
        int chickensize=chickens.size();
        if(chickensize!= 0){
            var chicken = chickens.get(0);
            for (int i = 0; i < chickensize; i++) {
                cardFactory.makeCards(1,new CardFactorySettings("Egg").withPosition(chicken.getPosition()));

            }
        }
    }
}
