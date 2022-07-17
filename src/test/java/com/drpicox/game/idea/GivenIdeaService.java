package com.drpicox.game.idea;

import org.springframework.stereotype.Service;

import static com.drpicox.game.idea.IdeaFactory.getIdFromName;

@Service
public class GivenIdeaService {

    private final IdeaFactory ideaFactory;
    private final IdeaRepository ideaRepository;

    public GivenIdeaService(IdeaFactory ideaFactory, IdeaRepository ideaRepository) {
        this.ideaFactory = ideaFactory;
        this.ideaRepository = ideaRepository;
    }

    public void givenIdea(String ideaName, int level, int xp) {
        var ideaId = getIdFromName(ideaName);
        if (ideaRepository.existsById(ideaId)) ideaRepository.deleteById(ideaId);
        ideaFactory.makeIdea(new IdeaFactorySettings(ideaName).withLevel(level).withXp(xp));
    }

    public void givenIdea(String ideaName) {
        givenIdea(ideaName, 1, 0);
    }

}
