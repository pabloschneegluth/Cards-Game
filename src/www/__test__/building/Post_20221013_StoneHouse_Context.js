import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221013_StoneHouse_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(
    n1,
    s1,
    n2,
    s2,
    n3,
    s3
  ) {
    // text:  * Given a new game with a stack of 1 "Build Idea" cards, 1 "Villager" cards and 4 "Stone" cards
    // code: await this.givenANewGameWithAStackOfNSCardsNSCardsAndNSCards(1, "Build Idea", 1, "Villager", 4, "Stone")
    // hint: Post_20221020_BuildIdea_Context.givenANewGameWithAStackOfNSCardsNSCardAndNSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221024_Pickaxe_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3
  ) {
    // text:  * There should be 1 stacks of 1 "Build Idea" cards, 1 "Villager" cards and 1 "Stone House" cards
    // code: await this.thereShouldBeNStacksOfNSCardsNSCardsAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Stone House")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
