import { render } from "@testing-library/react";
import { Provider } from "react-redux";
import { MemoryRouter } from "react-router";
import { App } from "../../App";
import { createAppStore } from "../../createAppStore";
import { setMainView } from "../main";

export function renderApp() {
  const result = render(
    <Provider store={createAppStore()}>
      <MemoryRouter>
        <App />
      </MemoryRouter>
    </Provider>,
  );

  setMainView(result.container);
}
