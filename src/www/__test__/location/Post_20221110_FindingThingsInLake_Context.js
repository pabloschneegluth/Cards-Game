import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221110_FindingThingsInLake_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSNSNSAndNSCards(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3,
    n4,
    s4
  ) {
    // text:  * Given a new game with a stack of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager" and 1 "Fishing Rod" cards.
    // code: await this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Lake Stroll Idea", 1, "Lake", 1, "Villager", 1, "Fishing Rod")
    // hint: Post_20221122_Hammer_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSNSNSAndNSCards(n1, s1, n2, s2, n3, s3, n4, s4) is not implemented yet."
    );
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Lake Stroll Idea" may create a "Fish" card.
    // code: await this.theSMayCreateASCard("Lake Stroll Idea", "Fish")
    // hint: Post_20221114_Chicken_Context.theSMayCreateASCard

    throw new Error(
      "The method theSMayCreateASCard(s1, s2) is not implemented yet."
    );
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Fish" from the "Lake" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Fish", "Lake")
    // hint: Post_20221114_Chicken_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

    throw new Error(
      "The method givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSNSNSNSAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4,
    n6,
    s5
  ) {
    // text:  * There should be 1 stacks of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager", 1 "Fishing Rod" and 1 "Fish" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSNSAndNSCards(1, 1, "Lake Stroll Idea", 1, "Lake", 1, "Villager", 1, "Fishing Rod", 1, "Fish")
    // hint: Post_20221105_Coal_Context.thereShouldBeNStacksOfNSNSNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3, n5, s4, n6, s5) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
