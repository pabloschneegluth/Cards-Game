package com.drpicox.game.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TagRepository extends JpaRepository<Tag, TagId> {
    List<Tag> findAllByTagName(String tagName);

}
