export const REPLACE_LOADING_SPINNER = "loading/REPLACE_LOADING_SPINNER";
function replaceLoadingSpinner(loading) {
  return { type: REPLACE_LOADING_SPINNER, loading };
}

export function showLoadingSpinner() {
  return replaceLoadingSpinner(true);
}

export function hideLoadingSpinner() {
  return replaceLoadingSpinner(false);
}

export function selectIsLoading(state) {
  return state.loading;
}

export function loadingReducer(state = false, action) {
  switch (action.type) {
    case REPLACE_LOADING_SPINNER:
      return action.loading;
    default:
      return state;
  }
}
