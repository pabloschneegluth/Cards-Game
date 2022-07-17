package com.drpicox.game.synthetics;


import com.drpicox.game.card.*;
import com.drpicox.game.card.api.CardListDTO;
import com.drpicox.game.card.api.StackListDTO;
import com.drpicox.game.idea.GivenIdeaService;
import com.drpicox.game.idea.IdeaService;
import com.drpicox.game.idea.api.IdeaListDTO;
import com.drpicox.game.util.DatabaseTestUtils;
import com.drpicox.game.game.api.GameDTO;
import com.drpicox.game.game.api.GameDTOFactory;
import com.drpicox.game.util.Positions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.drpicox.game.card.api.StackListDTO.*;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class CardStackTest {

    @Test @Transactional
    public void test_stacks() {
        given("A,U").whenStack("A>U").then(",UA");

        given("AB,V").whenStack("V>B").then("ABV");
        given("AB,V").whenStack("V>A").then("ABV");
        given("ABC,V").whenStack("V>C").then("ABCV");
        given("ABC,V").whenStack("V>B").then("ABCV");
        given("ABC,V").whenStack("V>A").then("ABCV");

        given("AB,CM,V").whenStack("V>B").then("ABV,CM");
        given("AB,CM,V").whenStack("V>A").then("ABV,CM");
        given("AB,CM,V").whenStack("V>M").then("AB,CMV");
        given("AB,CM,V").whenStack("V>C").then("AB,CMV");

        given("ABC,MVU").whenStack("U>C").then("ABCU,MV");
        given("ABC,MVU").whenStack("U>B").then("ABCU,MV");
        given("ABC,MVU").whenStack("U>A").then("ABCU,MV");
        given("ABC,MVU").whenStack("V>C").then("ABCVU,M");
        given("ABC,MVU").whenStack("V>B").then("ABCVU,M");
        given("ABC,MVU").whenStack("V>A").then("ABCVU,M");
        given("ABC,MVU").whenStack("M>C").then("ABCMVU");
        given("ABC,MVU").whenStack("M>B").then("ABCMVU");
        given("ABC,MVU").whenStack("M>A").then("ABCMVU");
        given("ABC,MVU").whenStack("A>U").then(",MVUABC");

        given("ABCM").whenStack("M>A").then("ABCM");
        given("ABCM").whenStack("M>B").then("ABCM");
        given("ABCM").whenStack("M>C").then("ABCM");
        given("ABCM").whenStack("M>M").then("ABCM");
        given("ABCM").whenStack("A>A").then("ABCM");
        given("ABCM").whenStack("A>B").then("ABCM");
        given("ABCM").whenStack("A>C").then("ABCM");
        given("ABCM").whenStack("A>M").then("ABCM");
        given("ABCM").whenStack("B>A").then("ABCM");
        given("ABCM").whenStack("B>B").then("ABCM");
        given("ABCM").whenStack("B>C").then("ABCM");
        given("ABCM").whenStack("B>M").then("ABCM");

        given("ABCM").whenStack("A>0").then("ABCM");
        given("ABCM").whenStack("A>1").then(",ABCM");
        given("ABCM").whenStack("A>2").then(",,ABCM");
        given("ABCM").whenStack("B>0").then("ABCM");
        given("ABCM").whenStack("B>1").then("A,BCM");
        given("ABCM").whenStack("B>2").then("A,,BCM");
        given("ABCM").whenStack("C>0").then("ABCM");
        given("ABCM").whenStack("C>1").then("AB,CM");
        given("ABCM").whenStack("C>2").then("AB,,CM");
        given("ABCM").whenStack("M>0").then("ABCM");
        given("ABCM").whenStack("M>1").then("ABC,M");
        given("ABCM").whenStack("M>2").then("ABC,,M");

        given("ABCM,V,U").whenStack("A>0").then("ABCM,V,U");
        given("ABCM,V,U").whenStack("A>1").then(",VABCM,U");
        given("ABCM,V,U").whenStack("A>2").then(",V,UABCM");
        given("ABCM,V,U").whenStack("B>0").then("ABCM,V,U");
        given("ABCM,V,U").whenStack("B>1").then("A,VBCM,U");
        given("ABCM,V,U").whenStack("B>2").then("A,V,UBCM");
        given("ABCM,V,U").whenStack("C>0").then("ABCM,V,U");
        given("ABCM,V,U").whenStack("C>1").then("AB,VCM,U");
        given("ABCM,V,U").whenStack("C>2").then("AB,V,UCM");
        given("ABCM,V,U").whenStack("M>0").then("ABCM,V,U");
        given("ABCM,V,U").whenStack("M>1").then("ABC,VM,U");
        given("ABCM,V,U").whenStack("M>2").then("ABC,V,UM");

        given("ABCM").whenDiscard("M").then("ABC");
        given("ABCM").whenDiscard("C").then("AB");
        given("ABCM").whenDiscard("B").then("A");
        given("ABCM").whenDiscard("A").then("");

        given("").whenNewCard("A").then("A");
        given("").whenNewCard("A").whenNewCard("B").then("AB");
        given("A").whenNewCard("B").then("AB");
        given("AB").whenNewCard("C").then("ABC");
        given("A,B").whenNewCard("C").then("AC,B");
        given(",A").whenNewCard("B").then("B,A");

        given("ABCM").whenDeleteCard("A").then("BCM");
        given("ABCM").whenDeleteCard("B").then("ACM");
        given("ABCM").whenDeleteCard("C").then("ABM");
        given("ABCM").whenDeleteCard("M").then("ABC");

        given("ABCM,V,U").whenMoveCard("A>0").then("ABCM,V,U");
        given("ABCM,V,U").whenMoveCard("A>1").then("BCM,AV,U");
        given("ABCM,V,U").whenMoveCard("A>2").then("BCM,V,AU");
        given("ABCM,V,U").whenMoveCard("B>0").then("BACM,V,U");
        given("ABCM,V,U").whenMoveCard("B>1").then("ACM,BV,U");
        given("ABCM,V,U").whenMoveCard("B>2").then("ACM,V,BU");
        given("ABCM,V,U").whenMoveCard("C>0").then("CABM,V,U");
        given("ABCM,V,U").whenMoveCard("C>1").then("ABM,CV,U");
        given("ABCM,V,U").whenMoveCard("C>2").then("ABM,V,CU");
        given("ABCM,V,U").whenMoveCard("M>0").then("MABC,V,U");
        given("ABCM,V,U").whenMoveCard("M>1").then("ABC,MV,U");
        given("ABCM,V,U").whenMoveCard("M>2").then("ABC,V,MU");

        given("ABCM,V,U").whenMoveCard("A>A").then("ABCM,V,U");
    }

    @Test @Transactional
    public void card_to_strings() {
        databaseTestUtils.clear();
        var settings = new CardFactorySettings("Apple").withPosition(0);
        cardFactory.makeCard(settings);
        cardFactory.makeCard(settings.withCardName("Berry"));
        cardFactory.makeCard(settings.withCardName("Villager"));

        var gameDTO = gameDTOFactory.makeGameDTO();
        var appleModel = cardService.findCard(byName("Apple")).get();
        var appleDTO = CardListDTO.getCard(gameDTO, byName("Apple"));
        var berryModel = cardService.findCard(byName("Berry")).get();
        var berryDTO = CardListDTO.getCard(gameDTO, byName("Berry"));
        var stackModel = cardPositionService.getStackByPosition(0);
        var stackDTO = StackListDTO.getStack(gameDTO, Positions.byPosition(0));

        assertThat(appleModel.toString()).contains("apple");
        assertThat(appleModel.toString()).contains("0");
        assertThat(berryModel.toString()).contains("berry");
        assertThat(berryModel.toString()).contains("0");
        assertThat(berryModel.toString()).contains("1");
        assertThat(appleDTO.toString()).contains("apple");
        assertThat(appleDTO.toString()).contains("0");
        assertThat(berryDTO.toString()).contains("berry");
        assertThat(berryDTO.toString()).contains("0");
        assertThat(berryDTO.toString()).contains("1");

        assertThat(stackModel.toString()).containsMatch(".*0.*apple.*berry.*villager.*");
        assertThat(stackDTO.toString()).containsMatch(".*0.*apple.*berry.*villager.*");
        assertThat(gameDTO.toString()).containsMatch(".*apple.*berry.*villager.*");
    }

    @Test @Transactional
    public void idea_to_strings() {
        databaseTestUtils.clear();
        var settings = new CardFactorySettings("Apple").withPosition(0);
        givenIdeaService.givenIdea("Harvest Idea", 2, 7);
        givenIdeaService.givenIdea("Woods Stroll Idea");

        var gameDTO = gameDTOFactory.makeGameDTO();
        var harvestModel = ideaService.findIdea(byName("Harvest Idea")).get();
        var harvestDTO = IdeaListDTO.getIdea(gameDTO, byName("Harvest Idea"));
        var strollModel = ideaService.findIdea(byName("Woods Stroll Idea")).get();
        var strollDTO = IdeaListDTO.getIdea(gameDTO, byName("Woods Stroll Idea"));

        assertThat(harvestModel.toString()).containsMatch(".*harvest.idea.*2.*7.*");
        assertThat(harvestDTO.toString()).containsMatch(".*harvest.idea.*2.*7.*");
        assertThat(strollModel.toString()).containsMatch(".*woods.stroll.idea.*");
        assertThat(strollDTO.toString()).containsMatch(".*woods.stroll.idea.*");

        assertThat(gameDTO.toString()).containsMatch(".*harvest.idea.*woods.stroll.idea.*");
    }

    @Test @Transactional
    public void test_given_card() {
        databaseTestUtils.clear();
        givenCardService.givenCards(1, "Apple");
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(1);

        givenCardService.givenCards(1, "Apple");
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(1);

        givenCardService.givenCards(3, "Apple");
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(3);

        givenCardService.givenCards(1, "Apple");
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(1);

        givenCardService.givenCards(2, new CardFactorySettings("Apple"));
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(2);

        givenCardService.givenCards(3, new CardFactorySettings("Apple").withPosition(1));
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(5);

        givenCardService.givenCards(5, new CardFactorySettings("Apple").withPosition(1));
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(7);

        givenCardService.givenCards(2, new CardFactorySettings("Apple").withPosition(1));
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(4);

        givenCardService.givenCards(1, new CardFactorySettings("Apple").withPosition(0));
        assertThat(cardService.findAllCards(byName("Apple"))).hasSize(3);
    }

    @Test @Transactional
    public void test_given_stack() {
        databaseTestUtils.clear();
        givenStackService.givenStacks(1, byNames("Apple", "Berry", "Villager"));
        assertThat(findAllStack(getGame(), byNames("Apple", "Berry", "Villager"))).hasSize(1);

        givenStackService.givenStacks(1, byNames("Apple", "Berry", "Villager"));
        assertThat(findAllStack(getGame(), byNames("Apple", "Berry", "Villager"))).hasSize(1);

        givenStackService.givenStacks(3, byNames("Apple", "Berry", "Villager"));
        assertThat(findAllStack(getGame(), byNames("Apple", "Berry", "Villager"))).hasSize(3);

        givenStackService.givenStacks(1, byNames("Apple", "Berry", "Villager"));
        assertThat(findAllStack(getGame(), byNames("Apple", "Berry", "Villager"))).hasSize(1);

        givenStackService.givenStacks(2, byNames("Apple", "Berry"));
        givenStackService.givenStacks(1, byNames("Apple", "Berry", "Villager"));
        assertThat(findAllStack(getGame(), byNames("Apple", "Berry"))).hasSize(2);
        assertThat(findAllStack(getGame(), byNames("Apple", "Berry", "Villager"))).hasSize(1);

        givenStackService.givenStacks(1, byNames("Militia"));
        assertThat(findAllStack(getGame(), byNames("Militia"))).hasSize(1);

        givenStackService.givenStackAt(5, byNames("Berry", "Berry Bush"));
        assertThat(findAllStack(getGame(), byNames("Berry", "Berry Bush"))).hasSize(1);
        assertThat(getStack(getGame(), Positions.byPosition(5)).getName(1)).isEqualTo("Berry Bush");

        givenStackService.givenStackAt(5, byNames("Apple", "Apple Tree"));
        assertThat(findAllStack(getGame(), byNames("Apple", "Apple Tree"))).hasSize(1);
        assertThat(getStack(getGame(), Positions.byPosition(5)).getName(1)).isEqualTo("Apple Tree");
    }

    @Test
    public void test_by_names() {
        assertThat(byNames().get())                          .isEqualTo(array());
        assertThat(byNames().and("Apple").get())             .isEqualTo(array("Apple"));
        assertThat(byNames().and("Apple").and("Berry").get()).isEqualTo(array("Apple", "Berry"));
        assertThat(byNames("Apple").get())                   .isEqualTo(array("Apple"));
        assertThat(byNames("Apple", "Berry").get())          .isEqualTo(array("Apple", "Berry"));
        assertThat(byNames("Apple").and( "Berry").get())     .isEqualTo(array("Apple", "Berry"));
        assertThat(byNames("Apple", "Apple Tree").and( "Berry", "Berry Bush").get()).isEqualTo(array("Apple", "Apple Tree", "Berry", "Berry Bush"));
        assertThat(byNames("Apple", "Apple Tree").and( "Berry", "Berry Bush").get()).isEqualTo(array("Apple", "Apple Tree", "Berry", "Berry Bush"));
        assertThat(byNames().and(3, "Apple").get())          .isEqualTo(array("Apple", "Apple", "Apple"));
        assertThat(byNames(2, "Berry").get())                .isEqualTo(array("Berry", "Berry"));
        assertThat(byNames(2, "Berry").and(3, "Apple").get()).isEqualTo(array("Berry", "Berry", "Apple", "Apple", "Apple"));
    }

    private String[] array(String ... array) {
        return array;
    }


    private GameDTO getGame() {
        return gameDTOFactory.makeGameDTO();
    }

    private StackTest given(String board) {
        return new StackTest().given(board);
    }

    @Autowired private CardLetterBoard cardLetterBoard;
    @Autowired private DatabaseTestUtils databaseTestUtils;
    @Autowired private CardService cardService;
    @Autowired private CardPositionService cardPositionService;
    @Autowired private StackService stackService;
    @Autowired private CardFactory cardFactory;
    @Autowired private IdeaService ideaService;
    @Autowired private GameDTOFactory gameDTOFactory;
    @Autowired private GivenCardService givenCardService;
    @Autowired private GivenStackService givenStackService;
    @Autowired private GivenIdeaService givenIdeaService;

    private class StackTest {
        public StackTest given(String board) {
            databaseTestUtils.clear();
            cardLetterBoard.makeBoard(board);
            return this;
        }

        public StackTest whenStack(String movement) {
            var from = movement.substring(0, 1);
            var to = movement.substring(2, 3);

            if (Character.isLetter(to.charAt(0))) whenStackToCard(from, to);
            else whenStackToPosition(from, Integer.parseInt(to));

            return this;
        }

        public void whenStackToCard(String originLetter, String targetLetter) {
            var originCard = cardLetterBoard.getCard(originLetter);
            var targetCard = cardLetterBoard.getCard(targetLetter);

            stackService.splitAndStackOnTopOf(originCard, targetCard);
        }

        public void whenStackToPosition(String originLetter, int targetPosition) {
            var originCard = cardLetterBoard.getCard(originLetter);

            stackService.splitAndStackOnTopOf(originCard, targetPosition);
        }

        public StackTest whenDiscard(String letter) {
            var card = cardLetterBoard.getCard(letter);
            stackService.splitAndDiscard(card);
            return this;
        }

        public StackTest whenNewCard(String cardLetter) {
            cardLetterBoard.makeCard(cardLetter);
            return this;
        }

        public StackTest whenDeleteCard(String letter) {
            var card = cardLetterBoard.getCard(letter);
            cardService.discardCard(card);
            return this;
        }

        public StackTest whenMoveCard(String movement) {
            var from = movement.substring(0, 1);
            var to = movement.substring(2, 3);
            if (Character.isLetter(to.charAt(0))) whenMoveCardToCard(from, to);
            else whenMoveCardToPosition(from, Integer.parseInt(to));
            return this;
        }

        public void whenMoveCardToCard(String from, String to) {
            var fromCard = cardLetterBoard.getCard(from);
            var toCard = cardLetterBoard.getCard(to);
            cardPositionService.moveCard(fromCard, toCard);
        }

        private void whenMoveCardToPosition(String letter, int targetPosition) {
            var card = cardLetterBoard.getCard(letter);
            cardPositionService.moveCard(card, targetPosition);
        }

        public StackTest then(String expectedBoard) {
            var actualServiceBoard = cardLetterBoard.getServiceBoard();
            var actualResponseBoard = cardLetterBoard.getResponseBoard();
            assertThat(actualServiceBoard).isEqualTo(expectedBoard);
            assertThat(actualResponseBoard).isEqualTo(expectedBoard);
            return this;
        }
    }

}
