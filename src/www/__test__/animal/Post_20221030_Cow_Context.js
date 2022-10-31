import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221030_Cow_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

    throw new Error(
      "Please, review the implementation of beforeTest() and remove this exception when it is correct."
    );
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20221024_Diamond_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();

    throw new Error(
      "The method givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Woods Stroll Idea" idea at level 4 and 20 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 4, 20)
    // hint: Post_20221020_BuildIdea_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();

    throw new Error(
      "The method givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) is not implemented yet."
    );
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Woods Stroll Idea" may create a "Cow" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Cow")
    // hint: Post_20221024_Diamond_Context.theSMayCreateASCard

    throw new Error(
      "The method theSMayCreateASCard(s1, s2) is not implemented yet."
    );
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Cow" from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Cow", "Woods Stroll Idea")
    // hint: Post_20221024_Diamond_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

    throw new Error(
      "The method givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221024_Diamond_Context.endTheCurrentMoon

    await waitForEndMoon();

    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Cow" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Cow")
    // hint: Post_20221024_Diamond_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
