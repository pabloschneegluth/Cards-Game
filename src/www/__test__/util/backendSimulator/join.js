export function join(...texts) {
  return texts
    .flat(Infinity)
    .filter((x) => x || x === "")
    .map((s) => (s.endsWith(" ") ? s : `${s}\n`))
    .join("");
}
