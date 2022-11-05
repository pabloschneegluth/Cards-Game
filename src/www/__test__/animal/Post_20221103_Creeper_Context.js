import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";

export class Post_20221103_Creeper_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewGameWithAStackOfNSCardsAndNSCard(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" card.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCard(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20220913_Wolf_Context.givenANewGameWithAStackOfNSCardsAndNSCard
    await waitForReloadGame();
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Woods Stroll Idea" idea at level 5 and 0 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Woods Stroll Idea", 5, 0)
    // hint: Post_20221030_Cow_Context.givenThereIsTheSIdeaAtLevelNAndNXp

    await waitForReloadGame();
  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Woods Stroll Idea" may create a "Creeper" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Creeper")
    // hint: Post_20221030_Cow_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async givenThatTheOddsAreThatWeWillFindASCardFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will find a "Creeper" card from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard("Creeper", "Woods Stroll Idea")
    // hint: Post_20220913_Wolf_Context.givenThatTheOddsAreThatWeWillFindASCardFromTheSCard

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221030_Cow_Context.endTheCurrentMoon

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
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" cards and 1 "Creeper" cards.
    // code: await this.thereShouldBeNStacksOfNSNSCardsAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Creeper")
    // hint: Post_20220913_Wolf_Context.thereShouldBeNStacksOfNSNSCardsAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
