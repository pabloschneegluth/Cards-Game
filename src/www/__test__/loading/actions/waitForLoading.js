import { screen, waitForElementToBeRemoved } from "@testing-library/react";

export async function waitForLoading() {
  const loading = screen.queryByTestId("loading");
  if (!loading)
    throw new Error(
      [
        `There is no loading. ` +
          `If you are getting some data from the backend, ` +
          `you should add the dispatch for showLoadingSpinner and hideLoadingSpinner. ` +
          `The code might look as follow:`,
        `export const myMiddleware = (store) => (next) => async (action) => {`,
        `  next(action);`,
        ``,
        `  if (action.type === MY_ACTION_TYPE) {`,
        `    store.dispatch(showLoadingSpinner());   // <<<<<<< Add This`,
        `    const data = await fetchMyData();`,
        `    store.dispatch(replaceMyData(data));`,
        `    store.dispatch(hideLoadingSpinner());  // <<<<<<< Add This`,
        `  }`,
        `};`,
      ].join("\n")
    );

  // ** If it fails add: store.dispatch(hideLoadingSpinner());
  await waitForElementToBeRemoved(() => screen.queryByTestId("loading"));
}
