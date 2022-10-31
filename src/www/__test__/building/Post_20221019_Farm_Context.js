import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221019_Farm_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async thereShouldBeNSCard(expected, s1) {
    // text:  * there should be 0 "Farm" card.
    // code: await this.thereShouldBeNSCard(0, "Farm")
    // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCard(expected, s1) is not implemented yet."
    );
  }

  async givenNSNSNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * given 1 "Build Idea", 1 "Stone", 2 "Wood" cards
    // code: await this.givenNSNSNSCards(1, "Build Idea", 1, "Stone", 2, "Wood")

    await waitForReloadGame();

    throw new Error(
      "The method givenNSNSNSCards(n1, s1, n2, s2, n3, s3) is not implemented yet."
    );
  }

  async givenThereIsTheSCardAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Build Idea" card at level 2 and 6 xp
    // code: await this.givenThereIsTheSCardAtLevelNAndNXp("Build Idea", 2, 6)

    await waitForReloadGame();

    throw new Error(
      "The method givenThereIsTheSCardAtLevelNAndNXp(s1, n1, n2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * end the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221024_Pickaxe_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNSNSAndNSCards(expected, s1, n2, s2, n3, s3) {
    // text:  * there should be 0 "Stone", 0 "Wood" and 0 "Berry Bush" cards.
    // code: await this.thereShouldBeNSNSAndNSCards(0, "Stone", 0, "Wood", 0, "Berry Bush")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSNSAndNSCards(expected, s1, n2, s2, n3, s3) is not implemented yet."
    );
  }

  async givenANewEmptyGame() {
    // text:  * Given a new empty game
    // code: await this.givenANewEmptyGame()

    await waitForReloadGame();

    throw new Error("The method givenANewEmptyGame() is not implemented yet.");
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Farm" cards.
    // code: await this.givenThereAreNSCards(1, "Farm")
    // hint: Post_20221020_WolfCallsWolf_Context.givenThereAreNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenThereAreNSCards(n1, s1) is not implemented yet."
    );
  }

  async givenThereAreNSCardsOrAnotherPlant(n1, s1) {
    // text:  * Given there are 1 "Berry Bush" cards or another plant.
    // code: await this.givenThereAreNSCardsOrAnotherPlant(1, "Berry Bush")

    await waitForReloadGame();

    throw new Error(
      "The method givenThereAreNSCardsOrAnotherPlant(n1, s1) is not implemented yet."
    );
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 5 "Berry" cards.
    // code: await this.thereShouldBeNSCards(5, "Berry")
    // hint: Post_20221020_WolfCallsWolf_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCards(expected, s1) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
