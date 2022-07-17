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

      <div className="panel">
        {posts.map((post) => (
          <BlogListItem key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}

function BlogListItem({ post }) {
  return (
    <div>
      <Link to={`/blog/${post.id}`} data-testid="post-list--item">
        {post.title}
      </Link>
      <small>
        <PostSummary post={post} />
      </small>
    </div>
  );
}
