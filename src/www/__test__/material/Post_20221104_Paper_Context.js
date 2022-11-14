import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221104_Paper_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSNSCardsAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 5 "Wood" cards, 1 "Build Idea" and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsNSAndNSCards(5, "Wood", 1, "Build Idea", 1, "Villager")
    // hint: Post_20221103_Key_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Build Idea" idea at level 2 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Build Idea", 2, 0)
    // hint: Post_20221103_OldVillage_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Build Idea" may create a "Paper" card.
    // code: await this.theSMayCreateASCard("Build Idea", "Paper")
    // hint: Post_20221103_Key_Context.theSMayCreateASCard
  }

  async givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Paper" card from the "Build Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Paper", "Build Idea")
    // hint: Post_20221103_Key_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221105_Wizard_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Build Idea", 1 "Villager" and 1 "Paper" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Build Idea", 1, "Villager", 1, "Paper")
    // hint: Post_20221103_Key_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
