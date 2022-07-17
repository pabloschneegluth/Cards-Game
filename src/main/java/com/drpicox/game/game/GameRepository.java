package com.drpicox.game.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GameRepository extends JpaRepository<Game, String> {
}
