package com.drpicox.game.building;

import org.springframework.stereotype.Component;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;

@Component
public class EndMoonStep_999_OtherBuilds implements EndMoonStep{
    private final CardService cardService;
    private final CardFactory cardFactory;

    public EndMoonStep_999_OtherBuilds(CardService cardService,CardFactory cardFactory) {
    this.cardService=cardService;
    this.cardFactory= cardFactory;
    }

    @Override
    public void execute(EndMoonSettings settings) {
        constructBarracks();

    }
    public void constructBarracks(){
        var villagers = cardService.findAllByName("Villager");
        var Wood = cardService.findAllByName("Wood");
        var Stone = cardService.findAllByName("Stone");
        var Iron = cardService.findAllByName("Iron");
        if((
            (villagers.size()==1)
            &&
            (Wood.size()==3)
            //&&
            //(Stone.size()==3)
            //&&
            //(Iron.size()==2)
        )){
                        cardFactory.makeCard(new CardFactorySettings("Barracks"));
                        cardService.discardCards(Wood);
                        cardService.discardCards(Stone);
                        cardService.discardCards(Iron);

        }else{
            return;
        }

    }

}
