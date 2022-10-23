import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221020_PearTree_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: await this.givenANewGame()
    // hint: Post_20220725_IdeasHaveLevels_Context.givenANewGame

    await waitForReloadGame();

    throw new Error("The method givenANewGame() is not implemented yet.");
  }

  async givenThereIsTheSIdea(s1) {
    // text:  * Given there is the "Harvest Idea" idea.
    // code: await this.givenThereIsTheSIdea("Harvest Idea")
    // hint: Post_20220725_IdeasHaveLevels_Context.givenThereIsTheSIdea

    await waitForReloadGame();

    throw new Error(
      "The method givenThereIsTheSIdea(s1) is not implemented yet."
    );
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Pear" cards.
    // code: await this.givenThereAreNSCards(1, "Pear")
    // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenThereAreNSCards(n1, s1) is not implemented yet."
    );
  }

  async givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Pear Tree" cards.
    // code: await this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Pear Tree")
    // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNStacksOfNSNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221020_WolfCallsWolf_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
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

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3, n5, s4) is not implemented yet."
    );
  }

  async theSCardDescriptionShouldSaySIsS(s1, s2, s3) {
    // text:  * The "Pear Tree" card description should say "Fruit" is "Pear".
    // code: await this.theSCardDescriptionShouldSaySIsS("Pear Tree", "Fruit", "Pear")
    // hint: Post_20220725_IdeasHaveLevels_Context.theSCardDescriptionShouldSaySIsS

    throw new Error(
      "The method theSCardDescriptionShouldSaySIsS(s1, s2, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
