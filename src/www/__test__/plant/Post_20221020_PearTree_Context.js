import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221020_PearTree_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Harvest Idea", 1 "Villager", and 1 "Pear Tree" cards.
    // code: await this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Harvest Idea", 1, "Villager", 1, "Pear Tree")

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221020_Pear_Context.endTheCurrentMoon

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

  async theSCardDescriptionShouldSaySIsS(s1, s2, s3) {
    // text:  * The "Pear Tree" card description should say "Fruit" is "Pear".
    // code: await this.theSCardDescriptionShouldSaySIsS("Pear Tree", "Fruit", "Pear")
    // hint: Post_20220725_IdeasHaveLevels_Context.theSCardDescriptionShouldSaySIsS


  }

  async givenANewGameWithAStackOfNS(n1, s1) {
    // text:  * Given a new game with a stack of 5 "Berry".
    // code: await this.givenANewGameWithAStackOfNS(5, "Berry")

    await waitForReloadGame();

  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Seed Idea" idea at level 2 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Seed Idea", 2, 0)
    // hint: Post_20221020_BuildIdea_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();

  }

  async givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given there are 1 stacks of 1 "Seed Idea", 1 "Villager", and 1 "Pear" cards.
    // code: await this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Seed Idea", 1, "Villager", 1, "Pear")
    // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNStacksOfNSNSAndNSCards

    await waitForReloadGame();

  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 0 "Pear Tree" cards.
    // code: await this.givenThereAreNSCards(0, "Pear Tree")
    // hint: Post_20221020_Pear_Context.givenThereAreNSCards

    await waitForReloadGame();

  }

  async theSCardShouldHaveNInSTag(s1, n1, s2) {
    // text:  * The "Pear" card should have 1 in "Seed" tag.
    // code: await this.theSCardShouldHaveNInSTag("Pear", 1, "Seed")
    // hint: Post_20220725_IdeasHaveLevels_Context.theSCardShouldHaveNInSTag

  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 1 "Pear Tree" cards.
    // code: await this.thereShouldBeNSCards(1, "Pear Tree")
    // hint: Post_20221020_Pear_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
