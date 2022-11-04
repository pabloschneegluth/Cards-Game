package com.drpicox.game.animal;

import org.springframework.stereotype.Component;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;

@Component
public class EndMoonStep_200_CowProduceMilk implements EndMoonStep{
    private final CardService cardService;
    private final CardFactory cardFactory;

    public EndMoonStep_200_CowProduceMilk(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory=cardFactory;
    }


    @Override
    public void execute(EndMoonSettings settings) {
        var cows = cardService.findAllByName("Cow");
        int cowsize=cows.size();
        if(cowsize!= 0){
            for (int i = 0; i < cowsize; i++) {
                cardFactory.makeCards(1,new CardFactorySettings("Milk"));

            }
        }


    }

}
