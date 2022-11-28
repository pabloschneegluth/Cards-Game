import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {queryAllCardByName, queryAllCardDigest} from "../card/queries";

export class Post_20221119_Meat_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 4 "Villager" and 1 "Meat" cards
    // code: await this.givenANewGameWithAStackOfNSAndNSCards(4, "Villager", 1, "Meat")
    // hint: Post_20221119_HowToKillAnimals_Context.givenANewGameWithAStackOfNSAndNSCards

    await waitForReloadGame();
  }

  async theSumOfAllSTagsValueShouldBeN(s1, expected) {
    // text:  * The sum of all "Eats" tags value should be 4.
    // code: await this.theSumOfAllSTagsValueShouldBeN("Eats", 4)
    // hint: Post_20221120_Egg_Context.theSumOfAllSTagsValueShouldBeN

    var cards = queryAllCardDigest(mainView);
    var actual = cards.reduce((acc, c) => acc + c.getTag(s1), 0);
    expect(actual).toEqual(expected);
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 4 "Villager" cards.
    // code: await this.thereShouldBeNSCards(4, "Villager")
    // hint: Post_20221114_WolfKillVillager_Context.thereShouldBeNSCards

    var actual = queryAllCardByName(mainView, s1);
    expect(actual).toHaveLength(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
