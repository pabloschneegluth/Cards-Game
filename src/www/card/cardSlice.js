import { combineReducers } from "redux";
import { backend } from "../backend";
import { replaceGame, REPLACE_GAME } from "../game/gameSlice";
import {
  hideLoadingSpinner,
  showLoadingSpinner,
} from "../loading/loadingSlice";

export function selectCard(state, cardId) {
  return state.card.byId[cardId];
}

export function selectActiveCardId(state) {
  return state.card.activeCardId;
}

const REPLACE_CARDS = "cards/REPLACE_CARDS";
export function replaceCards(cards) {
  return { type: REPLACE_CARDS, cards };
}

const ACTIVATE_CARD = "cards/ACTIVATE_CARD";
export function activateCard(cardId) {
  return { type: ACTIVATE_CARD, cardId };
}

const REQUEST_DISCARD_ACTIVE_CARD = "cards/REQUEST_DISCARD_ACTIVE_CARD";
export function requestDiscardActiveCard() {
  return { type: REQUEST_DISCARD_ACTIVE_CARD };
}

export const cardReducer = combineReducers({
  byId: cardByIdReducer,
  allIds: cardIdsReducer,
  activeCardId: activeCardReducer,

});

function activeCardReducer(state = null, action) {
  switch (action.type) {
    case ACTIVATE_CARD:
      return action.cardId;
    case REPLACE_GAME:
      return null;
    default:
      return state;
  }
}

function cardByIdReducer(state = {}, action) {
  switch (action.type) {
    case REPLACE_CARDS:
      return Object.fromEntries(action.cards.map((c) => [c.id, c]));
    default:
      return state;
  }
}

function cardIdsReducer(state = [], action) {
  switch (action.type) {
    case REPLACE_CARDS:
      return action.cards.map((c) => c.id);
    default:
      return state;
  }
}

async function fetchDiscardCard(cardId) {
  return backend.post(`/api/v1/game/cards/${cardId}/discard`, null);
}

export const cardMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REPLACE_GAME) {
    store.dispatch(replaceCards(action.game.cards));
  }
  if (action.type === REQUEST_DISCARD_ACTIVE_CARD) {
    var cardId = selectActiveCardId(store.getState());
    store.dispatch(showLoadingSpinner());
    const game = await fetchDiscardCard(cardId);
    store.dispatch(replaceGame(game));
    store.dispatch(hideLoadingSpinner());
  }
};
