function join(...array) {
  return (
    array
      .flat(Infinity)
      .filter((x) => x || x === "")
      .join("\n") + "\n"
  );
}
exports.join = join;
