package com.drpicox.game.idea;

import com.drpicox.game.util.HasName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> findAll() {
        return ideaRepository.findAll();
    }

    public Optional<Idea> findByName(String expected) {
        return ideaRepository.findByName(expected);
    }

    public void increaseXp(Idea idea) {
        idea.increaseXp();
        ideaRepository.save(idea);
    }

    public Optional<Idea> findIdeaById(String ideaId) {
        return ideaRepository.findById(ideaId);
    }

    public Optional<Idea> findIdea(Predicate<? super Idea> predicate) {
        var all = findAll();
        var result = all.stream().filter(predicate).findAny();
        return result;
    }
}
