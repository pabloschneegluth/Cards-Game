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
