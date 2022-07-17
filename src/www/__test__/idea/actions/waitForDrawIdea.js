import { mainView } from "../../main";
import { click } from "../../main/actions";
import { waitForLoading } from "../../loading/actions/waitForLoading";
import { getIdeaByName } from "../queries";

export async function waitForDrawIdea(ideaName) {
  var idea = getIdeaByName(mainView, ideaName);
  click(idea);
  await waitForLoading();
}
