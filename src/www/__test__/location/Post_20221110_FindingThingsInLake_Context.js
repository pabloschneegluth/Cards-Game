import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";
import { Names } from "../util/Names";
import { queryAllStackDigestByCardNames } from "../stack/queries";

export class Post_20221110_FindingThingsInLake_Context {
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
    // text:  * Given a new game with a stack of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager" and 1 "Fishing Rod" cards.
    // code: await this.givenANewGameWithAStackOfNSNSNSAndNSCards(1, "Lake Stroll Idea", 1, "Lake", 1, "Villager", 1, "Fishing Rod")
    // hint: Post_20221122_Hammer_Context.givenANewGameWithAStackOfNSNSNSAndNSCards

    await waitForReloadGame();

  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Lake Stroll Idea" may create a "Fish" card.
    // code: await this.theSMayCreateASCard("Lake Stroll Idea", "Fish")
    // hint: Post_20221114_Chicken_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Fish" from the "Lake" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Fish", "Lake")
    // hint: Post_20221114_Chicken_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard

    await waitForReloadGame();

  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221114_WolfKillVillager_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeNStacksOfNSNSNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
    count4,
    name4,
    count5,
    name5,
  ) {
    // text:  * There should be 1 stacks of 1 "Lake Stroll Idea", 1 "Lake", 1 "Villager", 1 "Fishing Rod" and 1 "Fish" cards.
    // code: await this.thereShouldBeNStacksOfNSNSNSNSAndNSCards(1, 1, "Lake Stroll Idea", 1, "Lake", 1, "Villager", 1, "Fishing Rod", 1, "Fish")
    // hint: Post_20221105_Coal_Context.thereShouldBeNStacksOfNSNSNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .and(count4, name4)
      .and(count5, name5)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
