package com.drpicox.game.mineral;
import com.drpicox.game.card.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndMoonStep_666_makingCoalByBurningWood implements EndMoonStep {

    private final StackService stackService;
    private final CardFactory cardFactory;
    private final CardService cardService;

    public EndMoonStep_666_makingCoalByBurningWood(StackService stackService, CardFactory cardFactory, CardService cardService) {
        super();
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.cardService = cardService;
    }

    public void execute(EndMoonSettings settings) {
            burningWood();
    }

    private void burningWood() {
        var Wood = cardService.findAllByName("Wood");
        var flint = cardService.findAllByName("Flint");
        if((
            (flint.size()==1)
           // &&
           // (Wood.size()==1)

        )){
                        var flintpos = flint.get(0);
                        cardFactory.makeCards(2,new CardFactorySettings("Coal").withPosition(flintpos.getPosition()));
                        cardService.discardCards(Wood);
        }else{
            return;
        }
    }
}
