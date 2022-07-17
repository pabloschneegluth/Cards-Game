import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { queryAllStackDigestByCardNames } from "../stack/queries";
import { Names } from "../util/Names";
import { getIdeaByName, getIdeaDigestByName } from "./queries";

export class Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context {
  async beforeTest() {
    // Do your setup here, if necessary
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: await this.enterTheGame()
    // hint: Post_20220725_IdeasHaveLevels_Context.enterTheGame
    await waitForEnterTheGame();
  }

  async thereShouldBeTheSIdea(expectedName) {
    // text:  * There should be the "Harvest Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Harvest Idea")
    // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeTheSIdea

    var actual = getIdeaByName(mainView, expectedName);
    expect(actual).toBeInTheDocument();
  }

  async theSShouldHaveLevelNAndNXp(ideaName, level, xp) {
    // text:  * The "Harvest Idea" should have level 1 and 0 XP.
    // code: await this.theSShouldHaveLevelNAndNXp("Harvest Idea", 1, 0)

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea).toMatchObject({ level, xp });
  }

  async theSMayCreateASCard(ideaName, cardName) {
    // text:  * The "Woods Stroll Idea" may create a "Berry" card.
    // code: await this.theSMayCreateASCard("Woods Stroll Idea", "Berry")

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.mayCreateCards).toContain(cardName);
  }

  async theSIdeaShouldRequireNCardWithAtLeastNInSTag(
    ideaName,
    cardCount,
    tagValue,
    tagName,
  ) {
    // text:  * The "Seed Idea" idea should require 1 card with at least 1 in "Seed" tag
    // code: await this.theSIdeaShouldRequireTheSumOfNInSTagCards("Seed Idea", 1, 1, "Worker")

    var idea = getIdeaDigestByName(mainView, ideaName);
    expect(idea.tagRequirements).toMatchObject({
      [tagName]: {
        cardCount,
        tagValue,
      },
    });
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards() {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")

    await waitForReloadGame();
  }

  async givenThatTheOddsAreThatWeWillGetASFromTheSCard() {
    // text:  * Given that the odds are that we will get a "Berry" from the "Woods Stroll Idea" card.
    // code: await this.givenThatTheOddsAreThatWeWillGetASFromTheSCard("Berry", "Woods Stroll Idea")

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20220725_IdeasHaveLevels_Context.endTheCurrentMoon

    await waitForEndMoon();
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(
    expected,
    count1,
    name1,
    count2,
    name2,
    count3,
    name3,
  ) {
    // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
    // code: await this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var names = Names.byNames(count1, name1)
      .and(count2, name2)
      .and(count3, name3)
      .get();

    var stacks = queryAllStackDigestByCardNames(mainView, names);
    expect(stacks).toHaveLength(expected);
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp() {
    // text:  * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
    // code: await this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 1, 9)

    await waitForReloadGame();
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
