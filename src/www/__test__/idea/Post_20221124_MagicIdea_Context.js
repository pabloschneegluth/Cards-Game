import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import {getIdeaDigestByName} from "./queries";

export class Post_20221124_MagicIdea_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();

  }

  async givenANewGameWithAStackOfNSCard(n1, s1) {
    // text:  * Given a new game with a stack of 1 "Wizard" card.
    // code: await this.givenANewGameWithAStackOfNSCard(1, "Wizard")
    // hint: Post_20221106_Archer_Context.givenANewGameWithAStackOfNSNSCards

    await waitForReloadGame();
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: await this.endTheCurrentMoon()
    // hint: Post_20221031_FishingRod_Context.endTheCurrentMoon

    await waitForEndMoon();

  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Old Village Stroll Idea" idea.
    // code: await this.thereShouldBeTheSIdea("Old Village Stroll Idea")
    // hint: Post_20221115_LakeStrollIdea_Context.thereShouldBeTheSIdea

    var idea = getIdeaDigestByName(mainView, expected);
    expect(idea.name).toMatch(expected)
  }

  async theSShouldHaveLevelNAndNXp(s1, n1, n2) {
    // text:  * The "Old Village Stroll Idea" should have level 1 and 0 XP.
    // code: await this.theSShouldHaveLevelNAndNXp("Old Village Stroll Idea", 1, 0)
    // hint: Post_20221115_LakeStrollIdea_Context.theSShouldHaveLevelNAndNXp

    var idea = getIdeaDigestByName(mainView, s1);
    expect(idea.level).toEqual( n1 );
    expect(idea.xp).toEqual( n2 );

  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
