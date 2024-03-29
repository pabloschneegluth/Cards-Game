import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getIdeaDigestByName} from "../idea/queries";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20221124_FindingThingsInSawmill_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSNSAndNSCards(n1, s1, n2, s2, n3, s3) {
    // text:  * Given a new game with a stack of 1 "Sawmill", 1 "Sawmill Stroll Idea" and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSNSAndNSCards(1, "Sawmill", 1, "Sawmill Stroll Idea", 1, "Villager")
    // hint: Post_20221129_Scorpion_Context.givenANewGameWithAStackOfNSNSAndNSCards

    await waitForReloadGame();

  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Sawmill" may create a "Wood" card.
    // code: await this.theSMayCreateASCard("Sawmill", "Wood")
    // hint: Post_20221128_Grizzly_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);

  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Wood" from the "Sawmill" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Wood", "Sawmill")
    // hint: Post_20221114_Chicken_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSNSNSAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4
  ) {
    // text:  * There should be 1 stacks of 1 "Sawmill Stroll Idea", 1 "Sawmill", 1 "Villager" and 2 "Wood" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSAndNSCards(1, 1, "Sawmill Stroll Idea", 1, "Sawmill", 1, "Villager", 2, "Wood")
    // hint: Post_20221129_Scorpion_Context.thereShouldBeNStacksOfNSNSNSAndNSCards

    var names = Names.byNames(n2, s1)
      .and(n3, s2)
      .and(n4, s3)
      .and(n5,s4)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
