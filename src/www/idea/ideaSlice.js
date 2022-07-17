import { combineReducers } from "redux";
import { backend } from "../backend";
import { replaceGame, REPLACE_GAME } from "../game/gameSlice";
import {
  hideLoadingSpinner,
  showLoadingSpinner,
} from "../loading/loadingSlice";

export function selectAllIdeaNames(state) {
  return state.idea.allNames;
}

export function selectIdea(state, ideaName) {
  return state.idea.byName[ideaName];
}

const REPLACE_IDEAS = "ideas/REPLACE_IDEAS";
export function replaceIdeas(ideas) {
  return { type: REPLACE_IDEAS, ideas };
}

const REQUEST_DRAW_IDEA = "ideas/REQUEST_DRAW_IDEA";
export function requestDrawIdea(ideaId) {
  return { type: REQUEST_DRAW_IDEA, ideaId };
}

export const ideaReducer = combineReducers({
  byName: ideaByNameReducer,
  allNames: ideaNamesReducer,
});

function ideaByNameReducer(state = {}, action) {
  switch (action.type) {
    case REPLACE_IDEAS:
      return Object.fromEntries(action.ideas.map((c) => [c.name, c]));
    default:
      return state;
  }
}

function ideaNamesReducer(state = [], action) {
  switch (action.type) {
    case REPLACE_IDEAS:
      return action.ideas.map((c) => c.name);
    default:
      return state;
  }
}

async function fetchDrawIdea(ideaId) {
  return backend.post(`/api/v1/game/ideas/${ideaId}/draw`, null);
}

export const ideaMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REPLACE_GAME) {
    store.dispatch(replaceIdeas(action.game.ideas));
  }
  if (action.type === REQUEST_DRAW_IDEA) {
    store.dispatch(showLoadingSpinner());
    const data = await fetchDrawIdea(action.ideaId);
    store.dispatch(replaceGame(data));
    store.dispatch(hideLoadingSpinner());
  }
};
