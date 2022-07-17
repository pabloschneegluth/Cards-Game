package com.drpicox.game.game;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {
    @Id
    private String id;

    public Game(String id) {
        this.id = id;
    }

    protected Game() {} // JPA
}
