import { combineReducers } from "redux";
import { backend } from "../backend";
import { selectActiveCardId } from "../card/cardSlice";
import { replaceGame, REPLACE_GAME } from "../game/gameSlice";
import {
  hideLoadingSpinner,
  showLoadingSpinner,
} from "../loading/loadingSlice";

export function selectAllStackPositions(state) {
  return state.stack.allPositions;
}

export function selectStack(state, position) {
  return state.stack.byPosition[position];
}

const REPLACE_STACKS = "stack/REPLACE_STACKS";
export function replaceStacks(stacks) {
  return { type: REPLACE_STACKS, stacks };
}

const REQUEST_MOVE_ACTIVE_CARD = "stack/REQUEST_MOVE_ACTIVE_CARD";
export function requestMoveActiveCard(position, zindex) {
  return { type: REQUEST_MOVE_ACTIVE_CARD, position, zindex };
}

export const stackReducer = combineReducers({
  byPosition: stackByPositionReducer,
  allPositions: stackPositionsReducer,
});

function stackByPositionReducer(state = {}, action) {
  switch (action.type) {
    case REPLACE_STACKS:
      return Object.fromEntries(action.stacks.map((s) => [s.position, s]));
    default:
      return state;
  }
}

function stackPositionsReducer(state = [], action) {
  switch (action.type) {
    case REPLACE_STACKS:
      return action.stacks.map((s) => s.position);
    default:
      return state;
  }
}

async function fetchMoveCard(cardId, { position, zindex }) {
  return backend.post(`/api/v1/game/cards/${cardId}/move`, {
    position,
    zindex,
  });
}

export const stackMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REPLACE_GAME) {
    var stacks = createStacksFromCards(action.game.cards);
    store.dispatch(replaceStacks(stacks));
  }
  if (action.type === REQUEST_MOVE_ACTIVE_CARD) {
    var cardId = selectActiveCardId(store.getState());
    store.dispatch(showLoadingSpinner()); // <<<<<<< Add This
    const data = await fetchMoveCard(cardId, action);
    store.dispatch(replaceGame(data));
    store.dispatch(hideLoadingSpinner()); // <<<<<<< Add This
  }
};

function createStacksFromCards(cards) {
  var stacks = [];
  var maxPosition = Math.max(...cards.map((c) => c.position));
  for (let position = 0; position <= maxPosition + 1; position++) {
    stacks.push({
      position: position,
      cardIds: cards.filter((c) => c.position === position).map((c) => c.id),
    });
  }
  return stacks;
}
