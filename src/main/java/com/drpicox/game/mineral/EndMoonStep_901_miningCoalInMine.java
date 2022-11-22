package com.drpicox.game.mineral;

import org.springframework.stereotype.Component;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;

@Component
public class EndMoonStep_901_miningCoalInMine implements EndMoonStep {

    private final StackService stackService;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_901_miningCoalInMine(StackService stackService, CardFactory cardFactory, CardService cardService) {
        super();
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
            miningInMine();
    }

    private void miningInMine() {
        var mine = cardService.findAllByName("Mine");
        var villager = cardService.findAllByName("Villager");
        var pickaxe = cardService.findAllByName("Pickaxe");
        var idea = cardService.findAllByName("Mine Stroll Idea");
        var coal = cardService.findAllByName("Coal");
        if((
            (!coal.isEmpty())
            &&
            (!mine.isEmpty())
            &&
            (!villager.isEmpty())
            &&
            (!pickaxe.isEmpty())
            &&
            (!idea.isEmpty())

        )){
                        var pickaxepos = pickaxe.get(0);
                        cardFactory.makeCards(1,new CardFactorySettings("Coal").withPosition(pickaxepos.getPosition()));

        }else{
            return;
        }
    }
}
