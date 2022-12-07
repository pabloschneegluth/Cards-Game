import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {Names} from "../util/Names";
import {queryAllStackDigestByCardNames} from "../stack/queries";

export class Post_20221127_Arpia_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCard(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Militia" card.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Woods Stroll Idea", 1, "Militia")
    // hint: Post_20221103_Creeper_Context.givenANewGameWithAStackOfNSCardsAndNSCard

    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Woods Stroll Idea" idea at level 5 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 5, 0)
    // hint: Post_20221128_Grizzly_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Woods Stroll Idea" may create a "Arpia" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Arpia")
    // hint: Post_20221128_Grizzly_Context.theSMayCreateASCard
  }

  async givenThatTheOddsAreThatWeWillFindASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will find a "Arpia" card from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard("Arpia", "Woods Stroll Idea")
    // hint: Post_20221103_Creeper_Context.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221117_FriendlyWolfKillWolf_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSCardsAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3
  ) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Militia" cards and 1 "Arpia" cards
    // code: await this.thereShouldBeNStacksOfNSNSCardsAndNSCards(1, 1, "Woods Stroll Idea", 1, "Militia", 1, "Arpia")
    // hint: Post_20221103_Creeper_Context.thereShouldBeNStacksOfNSNSCardsAndNSCards

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
