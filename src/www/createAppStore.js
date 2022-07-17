import { applyMiddleware, createStore, combineReducers, compose } from "redux";
import reduxFreeze from "redux-freeze";

import { blogReducer, blogMiddleware } from "./blog/blogSlice";
import { cardReducer, cardMiddleware } from "./card/cardSlice";
import { ideaReducer, ideaMiddleware } from "./idea/ideaSlice";
import { stackReducer, stackMiddleware } from "./stack/stackSlice";
import { gameMiddleware } from "./game/gameSlice";
import { loadingReducer } from "./loading/loadingSlice";

const reducer = combineReducers({
  blog: blogReducer,
  card: cardReducer,
  idea: ideaReducer,
  stack: stackReducer,
  loading: loadingReducer,
});

const middleware = [
  blogMiddleware,
  cardMiddleware,
  gameMiddleware,
  ideaMiddleware,
  stackMiddleware,
  reduxFreeze,
];

export function createAppStore(composeEnhancers = compose) {
  return createStore(reducer, composeEnhancers(applyMiddleware(...middleware)));
}
