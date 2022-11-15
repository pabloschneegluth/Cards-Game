package com.drpicox.game.idea.walks;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardService;
import com.drpicox.game.card.StackService;
import com.drpicox.game.idea.*;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import com.drpicox.game.util.RandomPickerService;
import org.springframework.stereotype.Component;

@Component
public class EndMoonStep_200_OldVillageCreateOldVillageStrollIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Old Village Stroll Idea";
    private final CardFactory cardFactory;
    private final StackService stackService;
    private final CardService cardService;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;
    private final IdeaProgressService ideaProgressService;
    private final RandomPickerService randomPickerService;
    private final IdeaFactory ideaFactory;

    public EndMoonStep_200_OldVillageCreateOldVillageStrollIdea(CardFactory cardFactory,
                                                                StackService stackService,
                                                                CardService cardService,
                                                                IdeaEndMoonStepExecutor ideaEndMoonStepExecutor,
                                                                IdeaProgressService ideaProgressService,
                                                                RandomPickerService randomPickerService, IdeaFactory ideaFactory) {
        this.cardFactory = cardFactory;
        this.stackService = stackService;
        this.cardService = cardService;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
        this.ideaProgressService = ideaProgressService;
        this.randomPickerService = randomPickerService;
        this.ideaFactory = ideaFactory;
    }

    @Override
    public void execute(EndMoonSettings settings) {

      var stack =  stackService.findAllStack();
      for (var cards : stack) {
          var location = cards.getCards();
          var oldVillage = location.stream().filter( x -> x.getName().equals("Old Village")).findFirst();
          if(oldVillage.isEmpty()==false){
              ideaFactory.makeIdea(new IdeaFactorySettings("Old Village Stroll Idea"));
          }
      }



    }

}
