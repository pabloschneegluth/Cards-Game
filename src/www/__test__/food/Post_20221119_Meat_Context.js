import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221119_Meat_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 4 "Villager" and 1 "Meat" cards
    // code: await this.givenANewGameWithAStackOfNSAndNSCards(4, "Villager", 1, "Meat")
    // hint: Post_20221119_HowToKillAnimals_Context.givenANewGameWithAStackOfNSAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSAndNSCards(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async theSumOfAllSTagsValueShouldBeN(s1, expected) {
    // text:  * The sum of all "Eats" tags value should be 4.
    // code: await this.theSumOfAllSTagsValueShouldBeN("Eats", 4)
    // hint: Post_20221120_Egg_Context.theSumOfAllSTagsValueShouldBeN

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method theSumOfAllSTagsValueShouldBeN(s1, expected) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 4 "Villager" cards.
    // code: await this.thereShouldBeNSCards(4, "Villager")
    // hint: Post_20221114_WolfKillVillager_Context.thereShouldBeNSCards

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
