export function PostSummary({ post }) {
  return (
    <>
      {post.id.slice(0, 10)}
      {post.frontMatter.package && (
        <span className="post-package">{post.frontMatter.package}</span>
      )}
      , by: {post.frontMatter.writer}
      {post.frontMatter.coder && `, coder: ${post.frontMatter.coder}`}
    </>
  );
}
