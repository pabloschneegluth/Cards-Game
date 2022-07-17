const levenshtein = require("fast-levenshtein");

function methodNameDistance(a, b) {
  const thresholdDistance = Math.floor(Math.min(a.length / 2));
  const distance = levenshtein.get(a, b);
  if (distance < thresholdDistance) return distance;

  const aWords = splitWords(a);
  const bWords = splitWords(b);

  return thresholdDistance + arrayUnionSize(aWords, bWords);
}

exports.methodNameDistance = methodNameDistance;

function splitWords(a) {
  return a
    .replace(/([A-Z])/g, " $1")
    .toLowerCase()
    .split(" ");
}

function arrayUnionSize(a, b) {
  const set = new Set(a);
  b.forEach((item) => set.add(item));
  return set.size;
}
