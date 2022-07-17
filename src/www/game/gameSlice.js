import { backend } from "../backend";
import {
  hideLoadingSpinner,
  showLoadingSpinner,
} from "../loading/loadingSlice";

const REQUEST_GAME = "game/REQUEST_GAME";
export function requestGame() {
  return { type: REQUEST_GAME };
}

export const REPLACE_GAME = "game/REPLACE_GAME";
export function replaceGame(game) {
  return { type: REPLACE_GAME, game };
}

const REQUEST_END_MOON = "game/REQUEST_END_MOON";
export function requestEndMoon() {
  return { type: REQUEST_END_MOON };
}

async function fetchGame() {
  return backend.get(`/api/v1/game`);
}

async function fetchEndMoon() {
  return backend.post(`/api/v1/game/moon`, null);
}

export const gameMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REQUEST_GAME) {
    store.dispatch(showLoadingSpinner());
    const game = await fetchGame();
    store.dispatch(replaceGame(game));
    store.dispatch(hideLoadingSpinner());
  }

  if (action.type === REQUEST_END_MOON) {
    store.dispatch(showLoadingSpinner());
    const game = await fetchEndMoon();
    store.dispatch(replaceGame(game));
    store.dispatch(hideLoadingSpinner());
  }
};
