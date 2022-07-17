package com.drpicox.game.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CardRepository extends JpaRepository<Card, String> {

    List<Card> findAllByName(String name);
    List<Card> findAllByNameAndZindex(String name, int zindex);
    List<Card> findAllByPositionOrderByZindexAsc(int position);
    List<Card> findAllByOrderByPositionAsc();
    boolean existsByPosition(int freePosition);
}
