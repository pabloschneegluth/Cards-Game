import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221115_LakeStrollIdea_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSCardsAndNSCard(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Lake" cards and 1 "Villager" card.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Lake", 1, "Villager")
    // hint: Post_20221110_OldVillageStrollIdea_Context.givenANewGameWithAStackOfNSCardsAndNSCard

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSCardsAndNSCard(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20220723_Ideas_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Lake Stroll Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Lake Stroll Idea")
    // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeTheSIdea(expected) is not implemented yet."
    );
  }

  async theSShouldHaveLevelNAndNXp(s1, n1, n2) {
    // text:  * The "Lake Stroll Idea" should have level 1 and 0 XP.
    // code: await this.theSShouldHaveLevelNAndNXp("Lake Stroll Idea", 1, 0)
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSShouldHaveLevelNAndNXp

    throw new Error(
      "The method theSShouldHaveLevelNAndNXp(s1, n1, n2) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
