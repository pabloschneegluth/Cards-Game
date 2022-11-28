import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221122_Hammer_Context {
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
    // text:  * Given a new game with a stack of 1 "Build Idea", 1 "Villager", 1 "Wood" and 2 "Iron" cards.
    // code: await this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Build Idea", 1, "Villager", 1, "Wood", 2, "Iron")
    // hint: Post_20221024_Pickaxe_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * end the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221027_Militia_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected,
                                             count1,
                                             name1,
                                             count2,
                                             name2,
                                             count3,
                                             name3,) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Arrow" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Arrow")
    // hint: Post_20221103_Milk_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
