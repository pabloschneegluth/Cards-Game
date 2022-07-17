import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {
  getAllCardByName,
  getAllCardDigestByName,
  getCardDigestByName,
  queryAllCardByName,
} from "../card/queries";
import {
  waitForMoveCardToItsOwnStack,
  waitForMoveCardOnTopOf,
} from "../stack/actions";
import { getIdeaByName, getIdeaDigestByName, queryIdeaByName } from "./queries";
import { waitForDrawIdea } from "./actions";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20220725_IdeasHaveLevels_Context {
  async beforeTest() {
    // Do your setup here, if necessary
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: await this.enterTheGame()
    // hint: Post_20220723_Ideas_Context.enterTheGame
    await waitForEnterTheGame();
  }

  async thereShouldBeTheSIdea(expectedName) {
    // text:  * There should be the "Harvest Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

    var actual = getIdeaByName(mainView, expectedName);
    expect(actual).toBeInTheDocument();
  }

  async theSShouldHaveLevelNAndNXp(ideaName, level, xp) {
    // text:  * The "Harvest Idea" should have level 1 and 0 XP.
    // code: await this.theSShouldHaveLevelNAndNXp("Harvest Idea", 1, 0)

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea).toMatchObject({ level, xp });
  }

  async drawACardFromTheSIdea(ideaName) {
    // text:  * Draw a card from the "Harvest Idea" idea.
    // code: await this.drawACardFromTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.drawACardFromTheSIdea

    await waitForDrawIdea(ideaName);
  }

  async moveTheSCardToItsOwnStack(cardName) {
    // text:  * Move the "Harvest Idea" card to its own stack.
    // code: await this.moveTheSCardToItsOwnStack("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.moveTheSCardToItsOwnStack

    var [card] = getAllCardByName(mainView, cardName);
    await waitForMoveCardToItsOwnStack(card);
  }

  async moveTheSCardOnTopOfTheSCard(topCardName, bottomCardName) {
    // text:  * Move the "Villager" card on top of the "Harvest Idea" card.
    // code: await this.moveTheSCardOnTopOfTheSCard("Villager", "Harvest Idea")
    // hint: Post_20220723_Ideas_Context.moveTheSCardOnTopOfTheSCard

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
    // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async thereShouldBeNSCards(expected, cardName) {
    // text:  * There should be 1 "Berry" cards.
    // code: await this.thereShouldBeNSCards(1, "Berry")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNSCards

    var cards = queryAllCardByName(mainView, cardName);
    expect(cards).toHaveLength(expected);
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20220723_Ideas_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: await this.givenANewGame()
    // hint: Post_20220723_Ideas_Context.givenANewGame

    await waitForReloadGame();
  }

  async givenThereIsTheSIdea() {
    // text:  * Given there is the "Harvest Idea" idea.
    // code: await this.givenThereIsTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.givenThereIsTheSIdea

    await waitForReloadGame();
  }

  async givenThereAreNStacksOfNSNSAndNSCards() {
    // text:  * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
    // code: await this.givenThereAreNStacksOfNSNSAndNSCards(2, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
    // hint: Post_20220723_Ideas_Context.givenThereAreNStacksOfNSNSAndNSCards

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp() {
    // text:  * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 1, 9)

    await waitForReloadGame();
  }

  async givenThereAreNSCards() {
    // text:  * Given there are 1 "Berry" cards.
    // code: await this.givenThereAreNSCards(1, "Berry")
    // hint: Post_20220723_Ideas_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async givenANewGameWithNSProductionStack() {
    // text:  * Given a new game with 1 "Berry Bush" production stack.
    // code: await this.givenANewGameWithNSProductionStack(1, "Berry Bush")

    await waitForReloadGame();
  }

  async thereShouldBeNoSIdea(expected) {
    // text:  * There should be no "Plant Seed" idea.
    // code: await this.thereShouldBeNoSIdea("Plant Seed")
    // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

    var idea = queryIdeaByName(mainView, expected);
    expect(idea).not.toBeInTheDocument();
  }

  async givenThereAreNStacksOfNSCards() {
    // text:  * Given there are 1 stacks of 5 "Berry" cards.
    // code: await this.givenThereAreNStacksOfNSCards(1, 5, "Berry");

    await waitForReloadGame();
  }

  async theSIdeaShouldRequireNCardWithAtLeastNInSTag(
    ideaName,
    cardCount,
    tagValue,
    tagName,
  ) {
    // text:  * The "Seed Idea" idea should require 1 card with at least 1 in "Seed" tag
    // code: await this.theSIdeaShouldRequireTheSumOfNInSTagCards("Seed Idea", 1, 1, "Worker")

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.tagRequirements).toMatchObject({
      [tagName]: {
        cardCount,
        tagValue,
      },
    });
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // text:  * The "Berry" card should have 1 in "Seed" tag
    // code: await this.theSCardShouldHaveNInSTag("Berry", 1, "Seed")

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.tags).toMatchObject({ [tagName]: count });
  }

  async theSCardDescriptionShouldSaySIsS(cardName, term, description) {
    // text: * The "Berry" card description should say "Plant" is "Berry Bush".
    // code: await this.theSCardDescriptionShouldSaySIsS("Berry", "Plant", "Berry Bush");

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.terms).toMatchObject({ [term]: description });
  }

  async theSCardProgressShouldBeNOfN(cardName, progress, maxProgress) {
    // text:  * The "Seed Idea" card progress should be 1 of 5.
    // code: await this.theSCardProgressShouldBeNOfN("Seed Idea", 1, 5)

    const card = getCardDigestByName(mainView, cardName);
    expect(card.progress).toBe(progress);
    expect(card.maxProgress).toBe(maxProgress);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
