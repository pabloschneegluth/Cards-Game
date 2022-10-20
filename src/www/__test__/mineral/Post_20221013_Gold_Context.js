import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getIdeaDigestByName } from "../idea/queries";

export class Post_20221013_Gold_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewGameWithAStackOfNSCardsAndNSCards

    await waitForReloadGame();
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard(s1, s2) {
    // text:  * Given that the odds are that we will get a "Gold" from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Gold", "Woods Stroll Idea")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenThatTheOddsAreThatWeWillGetASFromTheSCard
    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20220725_IdeasHaveLevels_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Woods Stroll Idea" may create a "Gold" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Gold")
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.theSMayCreateASCard

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
// text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Gold" cards.
// code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1, "Gold")
// hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeNStacksOfNSNSAndNSCards
  }
  async afterTest() {
    // Do your teardown here, if necessary
  }
}
