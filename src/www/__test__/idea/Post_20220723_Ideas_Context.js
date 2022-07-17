import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaByName, getIdeaDigestByName } from "./queries";
import {
  getAllCardByName,
  getAllCardDigestByName,
  queryAllCardByName,
} from "../card/queries";
import { waitForDiscardCard } from "../card/actions";
import {
  waitForMoveCardToItsOwnStack,
  waitForMoveCardOnTopOf,
} from "../stack/actions";
import { waitForDrawIdea } from "./actions";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";
export class Post_20220723_Ideas_Context {
  async beforeTest() {
    // Do your setup here, if necessary
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: await this.enterTheGame()
    // hint: Post_20220717_BushesVillagersAndBerries_Context.enterTheGame
    await waitForEnterTheGame();
  }

  async thereShouldBeTheSIdea(expectedName) {
    // text:  * There should be the "Harvest Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Harvest Idea")

    var actual = getIdeaByName(mainView, expectedName);
    expect(actual).toBeInTheDocument();
  }

  async thereShouldBeNSCards(count, cardName) {
    // text:  * There should be 0 "Harvest Idea" cards.
    // code: await this.thereShouldBeNSCards(0, "Harvest Idea")
    // hint: Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, cardName);
    expect(actual).toHaveLength(count);
  }

  async drawACardFromTheSIdea(ideaName) {
    // text:  * Draw a card from the "Harvest Idea" idea.
    // code: await this.drawACardFromTheSIdea("Harvest Idea")

    await waitForDrawIdea(ideaName);
  }

  async givenThereIsTheSIdea() {
    // text:  * Given there is the "Harvest Idea" idea.
    // code: await this.givenThereIsTheSIdea("Harvest Idea")

    await waitForReloadGame();
  }

  async theSIdeaShouldRequireNCardWithAtLeastNInSTag(
    ideaName,
    cardCount,
    tagValue,
    tagName,
  ) {
    // text: * The "Harvest Idea" idea should require 1 card with at least 1 in "Fruit Plant" tag.
    // code: await this.theSIdeaShouldRequireNCardWithAtLeastNInSTag("Harvest Idea", 1, 1, "Fruit Plant")

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.tagRequirements).toMatchObject({
      [tagName]: {
        cardCount,
        tagValue,
      },
    });
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // example:  * The "berry" card has 1 in "food" tag.
    // the = "berry"
    // has = 1
    // arg2 = "food"

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.tags).toMatchObject({ [tagName]: count });
  }

  async theSCardDescriptionShouldSaySIsS(cardName, term, text) {
    // text: * The "Berry Bush" card description should say "Fruit" is "Berry".
    // code: await this.theSCardDescriptionShouldSaySIsS("Berry Bush", "Fruit"," Berry")

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.terms).toMatchObject({ [term]: text });
  }

  async discardNSCards(count, cardName) {
    // text:  * Discard 1 "Harvest Idea" cards.
    // code: await this.discardNSCards(1, "Harvest Idea")

    for (var i = 0; i < count; i++) {
      var [card] = getAllCardByName(mainView, cardName);
      await waitForDiscardCard(card);
    }
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: await this.givenANewGame()
    await waitForReloadGame();
  }

  async givenThereAreNSCards() {
    // text:  * Given there are 1 "Harvest Idea" cards.
    // code: await this.givenThereAreNSCards(1, "Harvest Idea")

    await waitForReloadGame();
  }

  async moveTheSCardToItsOwnStack(cardName) {
    // text:  * Move the "Berry Bush" card to its own stack.
    // code: await this.moveTheSCardToItsOwnStack("Berry Bush")

    var [card] = getAllCardByName(mainView, cardName);
    await waitForMoveCardToItsOwnStack(card);
  }

  async moveTheSCardOnTopOfTheSCard(topCardName, bottomCardName) {
    // text:  * Move the "Villager" card on top of the "Harvest Idea" card.
    // code: await this.stackNSCardOnTopOfTheSCard("Villager", "Harvest Idea")

    var [topCard] = getAllCardByName(mainView, topCardName);
    var [bottomCard] = getAllCardByName(mainView, bottomCardName);
    await waitForMoveCardOnTopOf(topCard, bottomCard);
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
  ) {
    // text:  * There should be 1 stacks of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async givenThereAreNStacksOfNSNSAndNSCards() {
    // text:  * Given there are 1 stacks of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: await this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20220719_VillagersEatFood_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async givenThereAreNStacksOfNSNSNSAndNSCards() {
    // text:  * Given there are 1 stacks of 1 "Corpse", 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: await this.givenThereAreNStacksOfNSNSNSAndNSCards(1, 1, "Corpse", 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    await waitForReloadGame();
  }

  async thereShouldBeNStacksOfNSNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
    count4,
    name4,
  ) {
    // text:  * There should be 1 stacks of 1 "Corpse", 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Corpse", 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .and(count4, name4)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
