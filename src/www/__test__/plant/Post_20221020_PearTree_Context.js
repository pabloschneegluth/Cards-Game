import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getAllCardDigestByName, queryAllCardByName} from "../card/queries";

export class Post_20221020_PearTree_Context {
  async beforeTest() {
    // Do your setup here
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: await this.givenANewGame()
    // hint: Post_20220723_Ideas_Context.givenANewGame

    await waitForReloadGame();
  }

  async givenThereIsTheSIdea(s1) {
    // text:  * Given there is the "Harvest Idea" idea.
    // code: await this.givenThereIsTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.givenThereIsTheSIdea

    await waitForReloadGame();


  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Pear" cards.
    // code: await this.givenThereAreNSCards(1, "Pear")
    // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

    await waitForReloadGame();

  }

  async givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Pear Tree" cards.
    // code: await this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Pear Tree")
    // hint: Post_20220723_Ideas_Context.givenThereAreNStacksOfNSNSAndNSCards

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221020_WolfCallsWolf_Context.endTheCurrentMoon
    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSNSAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4
  ) {
    // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", 1 "Pear Tree", and 2 "Pear" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Pear Tree", 2, "Pear")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async theSCardDescriptionShouldSaySIsS(cardName, term, description) {
    // text:  * The "Pear Tree" card description should say "Fruit" is "Pear".
    // code: await this.theSCardDescriptionShouldSaySIsS("Pear Tree", "Fruit", "Pear")
    // hint: Post_20220723_Ideas_Context.theSCardDescriptionShouldSaySIs

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.terms).toMatchObject({ [term]: description });
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Seed Idea" idea at level 2 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Seed Idea", 2, 0)
    // hint: Post_20221020_BuildIdea_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame()
  }

  async givenThereAreNStacksOfNSCards(n1, n2, s1) {
    // text:  * Given there are 1 stacks of 5 "Berry" cards.
    // code: await this.givenThereAreNStacksOfNSCards(1, 5, "Berry")
    // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNStacksOfNSCards

    await waitForReloadGame()
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // text:  * The "Pear" card should have 1 in "Seed" tag.
    // code: await this.theSCardShouldHaveNInSTag("Pear", 1, "Seed")
    // hint: Post_20220719_VillagersEatFood_Context.theSCardShouldHaveNInST

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.tags).toMatchObject({ [tagName]: count });
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }

  async thereShouldBeNSCards(number, pearTree) {
    var cards = queryAllCardByName(mainView, pearTree);
    expect(cards).toHaveLength(number);
  }
}
