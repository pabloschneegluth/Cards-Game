import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { HashRouter } from "react-router-dom";

import "./index.css";

import { App } from "./App";
import { createAppStore } from "./createAppStore";

const root = ReactDOM.createRoot(document.getElementById("root"));
const store = createAppStore(
  global.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({ trace: true }),
);

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <HashRouter>
        <App />
      </HashRouter>
    </Provider>
  </React.StrictMode>,
);
