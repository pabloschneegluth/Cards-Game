package com.drpicox.game.idea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface IdeaRepository extends JpaRepository<Idea, String> {
    Optional<Idea> findByName(String name);
}
