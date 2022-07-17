import { mainView } from "../../main";
import { clickButton } from "../../main/actions/click";
import { waitForLoading } from "../../loading/actions/waitForLoading";

export async function waitForEndMoon() {
  clickButton(mainView, "End Moon");
  await waitForLoading();
}
