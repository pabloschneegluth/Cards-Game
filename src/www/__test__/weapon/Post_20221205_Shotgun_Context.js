import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221205_Shotgun_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
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
    // text:  * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 3 "Wood" and 2 "Iron" cards.
    // code: await this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 3, "Wood", 2, "Iron")
    // hint: Post_20221110_FindingThingsInLake_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221207_PyramidStrollIdea_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager", 1 "Shotgun" cards
    // code: await this.thereShouldBeNStacksOfNSNSNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Shotgun")
    // hint: Post_20221024_Pickaxe_Context.thereShouldBeNStacksOfNSNSNSCards

    var names = Names.byNames(n2, s1)
      .and(n3, s2)
      .and(n4, s3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
