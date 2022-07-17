import { createSelector } from "reselect";
import { backend } from "../backend";
import {
  hideLoadingSpinner,
  showLoadingSpinner,
} from "../loading/loadingSlice";

function selectPostsById(state) {
  return state.blog;
}

export const selectPosts = createSelector(selectPostsById, (posts) =>
  Object.values(posts)
);

export function selectPost(state, postId) {
  return selectPostsById(state)[postId];
}

const REQUEST_POSTS = "blog/REQUEST_POSTS";
export function requestPosts() {
  return { type: REQUEST_POSTS };
}

const REQUEST_POST = "blog/REQUEST_POST";
export function requestPost(postId) {
  return { type: REQUEST_POST, postId };
}

const REPLACE_POSTS = "blog/REPLACE_POSTS";
export function replacePosts(posts) {
  return { type: REPLACE_POSTS, posts };
}

const EXTEND_POST = "blog/EXTEND_POST";
export function extendPost(post) {
  return { type: EXTEND_POST, post };
}

export function blogReducer(state = {}, action) {
  switch (action.type) {
    case REPLACE_POSTS:
      return Object.fromEntries(action.posts.map((post) => [post.id, post]));
    case EXTEND_POST:
      return {
        ...state,
        [action.post.id]: {
          ...state[action.post.id],
          ...action.post,
        },
      };
    default:
      return state;
  }
}

async function fetchPosts() {
  return backend.get("/api/v1/posts").then((r) => r.posts);
}

async function fetchPost(postId) {
  return backend.get(`/api/v1/posts/${postId}`);
}

export const blogMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REQUEST_POSTS) {
    store.dispatch(showLoadingSpinner());
    const posts = await fetchPosts();
    store.dispatch(replacePosts(posts));
    store.dispatch(hideLoadingSpinner());
  }

  if (action.type === REQUEST_POST) {
    store.dispatch(showLoadingSpinner());
    const post = await fetchPost(action.postId);
    store.dispatch(extendPost(post));
    store.dispatch(hideLoadingSpinner());
  }
};
