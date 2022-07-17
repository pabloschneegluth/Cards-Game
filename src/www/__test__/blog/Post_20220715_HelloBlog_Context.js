import { queryAllByTestId, getByTestId } from "@testing-library/react";
import { clickLink } from "../main/actions";
import { waitForLoading } from "../loading/actions";
import { mainView } from "../main";

export class Post_20220715_HelloBlog_Context {
  async beforeTest() {}

  async goToTheBlogSection() {
    clickLink(mainView, "Blog");
    await waitForLoading();
  }

  async youShouldSeeAListOfPosts() {
    const posts = queryAllByTestId(mainView, "post-list--item");
    expect(posts).not.toHaveLength(0);
  }

  async theLastPostTitleShouldBeSThisPost(expected) {
    // expected = "Hello Blog"

    const posts = queryAllByTestId(mainView, "post-list--item");
    const last = posts.at(-1);

    expect(last).toHaveTextContent(expected);
  }

  async goToTheSPost(postTitle) {
    clickLink(mainView, postTitle);
    await waitForLoading();
  }

  async youShouldSeeTheSPost(the) {
    // the = "Hello Blog"

    const title = getByTestId(mainView, "post-title");
    expect(title).toHaveTextContent(the);
  }

  async thePostShouldContainSWhichIsHere(contain) {
    const body = getByTestId(mainView, "post-body");
    expect(body).toHaveTextContent(contain);
  }

  async afterTest() {}
}
