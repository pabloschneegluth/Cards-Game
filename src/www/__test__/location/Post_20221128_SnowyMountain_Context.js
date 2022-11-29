import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221128_SnowyMountain_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20221117_FriendlyWolfKillWolf_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Woods Stroll Idea" idea at level 3 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 3, 0)
    // hint: Post_20221114_Chicken_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Woods Stroll Idea" may create a "Snowy Mountain" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Snowy Mountain")
    // hint: Post_20221114_Chicken_Context.theSMayCreateASCard

  }

  async givenThatTheOddsAreThatWeWillGetASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Snowy Mountain" card from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard("Snowy Mountain", "Woods Stroll Idea")
    // hint: Post_20221103_Chest_Context.givenThatTheOddsAreThatWeWillGetASCardFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Snowy Mountain" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Snowy Mountain")
    // hint: Post_20221114_Chicken_Context.thereShouldBeNStacksOfNSNSAndNSCards

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
