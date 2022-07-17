function makeJavaName(postId) {
  const parts = postId.split("_");
  const date = parts[0].split("-").join("");
  const name = parts
    .slice(1)
    .map((p) => p[0].toUpperCase() + p.slice(1))
    .join("");
  const javaName = `${date}_${name}`;
  return javaName;
}
exports.makeJavaName = makeJavaName;
