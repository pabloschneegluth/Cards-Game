import { mainView } from "../../main";
import { clickButton } from "./click";
import { waitForLoading } from "./waitForLoading";

export async function waitForEnterTheGame() {
  clickButton(mainView, "Enter the Game");
  await waitForLoading();
}
