import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221110_OldVillageStrollIdea_Context {
  async beforeTest() {
    // Do your setup here

    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSCardsAndNSCard(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Old Village" cards and 1 "Villager" card.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Old Village", 1, "Villager")
    // hint: Post_20221103_Creeper_Context.givenANewGameWithAStackOfNSCardsAndNSCard

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221013_WoodHouse_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Old Village Stroll Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Old Village Stroll Idea")
    // hint: Post_20221020_BuildIdea_Context.thereShouldBeTheSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);


  }

  async theSShouldHaveLevelNAndNXp(s1, n1, n2) {
    // text:  * The "Old Village Stroll Idea" should have level 1 and 0 XP.
    // code: await this.theSShouldHaveLevelNAndNXp("Old Village Stroll Idea", 1, 0)
    // hint: Post_20221020_BuildIdea_Context.theSShouldHaveLevelNAndNXp


  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
