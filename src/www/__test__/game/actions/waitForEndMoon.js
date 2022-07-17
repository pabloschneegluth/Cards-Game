import { mainView } from "../../main";
import { clickButton } from "./click";
import { waitForLoading } from "./waitForLoading";

export async function waitForEndMoon() {
  clickButton(mainView, "End Moon");
  await waitForLoading();
}
