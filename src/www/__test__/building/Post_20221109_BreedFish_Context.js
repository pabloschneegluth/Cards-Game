import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221109_BreedFish_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: await this.givenANewGame()

    await waitForReloadGame();

  }

  async givenAStackOfNSAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a stack of 1 "Villager" and 1 "Fish farm" cards.
    // code: await this.givenAStackOfNSAndNSCards(1, "Villager", 1, "Fish farm")

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221128_Grizzly_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeAStackOfNSNSAndNSCards(expected, s1, n2, s2, n3, s3) {
    // text:  * There should be a stack of 1 "Villager", 1 "Fish farm" and 1 "Fish" cards.
    // code: await this.thereShouldBeAStackOfNSNSAndNSCards(1, "Villager", 1, "Fish farm", 1, "Fish")
    // hint: Post_20221019_Farm_Context.thereShouldBeNStackOfNSNSAndNSCards

    var names = Names.byNames(expected, s1)
      .and(n2, s2)
      .and(n3, s3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
