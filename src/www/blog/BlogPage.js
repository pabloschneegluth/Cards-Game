import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { selectPosts, requestPosts } from "./blogSlice";
import { PostSummary } from "./PostSummary";

export function BlogPage() {
  const posts = useSelector(selectPosts);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(requestPosts());
  }, [dispatch]);

  return (
    <div className="blog">
      <h1>Blog</h1>

      <PostCounts posts={posts} />
      <br />
      <PostsRanking posts={posts} />

      <h2>Implemented</h2>
      <div className="panel">
        {posts
          .filter((post) => post.frontMatter.coder)
          .map((post) => (
            <BlogListItem key={post.id} post={post} />
          ))}
      </div>

      <h2>Unimplemented</h2>
      <div className="panel">
        {posts
          .filter((post) => !post.frontMatter.coder)
          .map((post) => (
            <BlogListItem
              key={post.id}
              post={post}
              testId="unimplemented-post"
            />
          ))}
      </div>
    </div>
  );
}

function PostCounts({ posts }) {
  const implemented = posts.filter((post) => post.frontMatter.coder).length;
  const unimplemented = posts.filter((post) => !post.frontMatter.coder).length;

  return (
    <div className="post-counts">
      Counts:{" "}
      <span className="post-counts--implemented">
        {implemented} implemented
      </span>
      {", "}
      <span className="post-counts--unimplemented">
        {unimplemented} unimplemented
      </span>
    </div>
  );
}

function PostsRanking({ posts }) {
  const users = [
    ...new Set(posts.map((post) => post.frontMatter.writer)),
    "nobody",
  ];
  const stats = users.map((user) => ({
    user,
    writer: posts.filter((post) => post.frontMatter.writer === user).length,
    coder: posts.filter((post) => post.frontMatter.coder === user).length,
  }));
  const sortedStats = stats.sort(
    (a, b) => 999 * (b.coder - a.coder) + (b.writer - a.writer),
  );

  return (
    <div className="posts-ranking">
      <div>Authors:</div>
      {sortedStats
        .filter((state) => state.user !== "nobody")
        .map((stat) => (
          <div key={stat.user}>
            - {stat.user}: {stat.coder} implemented, {stat.writer} written
          </div>
        ))}
    </div>
  );
}

function BlogListItem({ post, testId = "post-list--item" }) {
  return (
    <div>
      <Link to={`/blog/${post.id}`} data-testid={testId}>
        {post.title}
      </Link>
      <small>
        <PostSummary post={post} />
      </small>
    </div>
  );
}
