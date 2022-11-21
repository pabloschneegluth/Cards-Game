package com.drpicox.game.mineral;

import org.springframework.stereotype.Component;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;

@Component
public class EndMoonStep_667_miningCoalInMine implements EndMoonStep {

    private final StackService stackService;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_667_miningCoalInMine(StackService stackService, CardFactory cardFactory, CardService cardService) {
        super();
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
            miningInMine();
    }

    private void miningInMine() {
        //There should be 1 stacks of 1 "Mine Stroll Idea", 1 "Mine", 1 "Villager", 1 "Pickaxe" and 1 "Coal" cards
        var mine = cardService.findAllByName("Mine");
        var villager = cardService.findAllByName("Villager");
        var pickaxe = cardService.findAllByName("Pickaxe");
        var idea = cardService.findAllByName("Mine Stroll Idea");
        var coal = cardService.findAllByName("Coal");
        if((
            (coal.size()==1)
            &&
            (mine.size()==1)
            &&
            (villager.size()==1)
            &&
            (pickaxe.size()==1)
            &&
            (idea.size()==1)

        )){
                        var pickaxepos = pickaxe.get(0);
                        cardFactory.makeCards(1,new CardFactorySettings("Coal").withPosition(pickaxepos.getPosition()));

        }else{
            return;
        }
    }
}
