package com.drpicox.game.idea;

import com.drpicox.game.game.GameDeleteStep;
import com.drpicox.game.game.GameFactorySettings;
import org.springframework.stereotype.Component;

@Component
public class GameDeleteStep_100_Ideas implements GameDeleteStep {

    private final IdeaRepository ideaRepository;

    public GameDeleteStep_100_Ideas(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @Override
    public void execute(GameFactorySettings settings) {
        ideaRepository.deleteAll();
    }
}
