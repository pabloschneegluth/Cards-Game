const chalk = require("chalk");
const { findClosestMethod } = require("./posts");
const { join } = require("./join");
const { methodNameDistance } = require("./methodNameDistance");

function debugPost(post) {
  const sortedMethods = [];
  let unusedMethods = post.contextMethods.slice();
  let current = unusedMethods.shift();
  if (!current) {
    console.log(
      join([
        `STATS FOR ${post.id}.md`,
        "Nothing to debug yet. ",
        '> Please add some " * Steps" to start the debugger.',
      ]),
    );
    return;
  }

  current.distance = 0;
  sortedMethods.push(current);
  while (unusedMethods.length) {
    let candidate = unusedMethods[0];
    let candidateDistance = methodNameDistance(current.name, candidate.name);
    for (let i = 1; i < unusedMethods.length; i += 1) {
      let newCandidate = unusedMethods[i];
      let newDistance = methodNameDistance(current.name, newCandidate.name);
      if (newDistance < candidateDistance) {
        candidate = newCandidate;
        candidateDistance = newDistance;
      }
    }
    unusedMethods = unusedMethods.filter((m) => m !== candidate);
    sortedMethods.push(candidate);
    candidate.distance = candidateDistance;
    current = candidate;
  }

  let closesMethods = sortedMethods.map((m) => findClosestMethod(post, m));

  let lines = sortedMethods.map((method) => {
    let result = ``;

    if (method.distance > 0) result += padLeft(` ∆${method.distance}`, 4);
    else result += `    `;

    let calls = post.testCalls.filter((call) => call.name === method.name);
    result += padLeft(`${calls.length}x`, 4);

    result += `${method.text}`;
    return result;
  });

  let maxLength = lines.reduce(
    (max, line) => (line.length > max ? line.length : max),
    0,
  );

  lines = lines.map((line) => padRight(line, maxLength + 1));

  lines = lines.map((line, index) => {
    let closestMethod = closesMethods[index];
    const { distance } = closestMethod;
    const example =
      distance > 0
        ? `${padLeft(`∆${distance}`, 3)}${closestMethod.text}`
        : "found";
    const at = `${closestMethod.postIds.join(", ")}`;
    line += ` (${example} at: ${at})`;

    return line;
  });

  let maxWith = process.stdout.columns;
  lines = lines.map((line) =>
    line.length > maxWith ? `${line.slice(0, maxWith - 1)}…` : line,
  );

  lines = lines.map((line, index) => {
    let color = (i) => i;
    let method = sortedMethods[index];
    let closestMethod = closesMethods[index];

    if (closestMethod.distance < 3) color = chalk.yellow;
    if (closestMethod.distance === 0) color = chalk.green.bold;
    if (1 <= method.distance && method.distance <= 3) color = chalk.yellow;

    var prevMethod = sortedMethods[index - 1];
    var nextMethod = sortedMethods[index + 1];
    if (
      (closestMethod.distance === 1 &&
        thereIsASmallFix(method.name, closestMethod.name)) ||
      (method.distance === 1 &&
        thereIsASmallFix(method.name, prevMethod.name)) ||
      (nextMethod?.distance === 1 &&
        thereIsASmallFix(method.name, nextMethod.name))
    )
      color = chalk.red.bold;

    return color(line);
  });

  console.log(
    join(
      `STATS FOR ${post.id}.md`,
      `Total calls: ${post.testCalls.length}`,
      `Total methods: ${post.contextMethods.length}`,
      `Methods sorted by similitude:`,
      lines,
    ),
  );
}

exports.debugPost = debugPost;

function padLeft(text, width) {
  text = `${text}`;
  while (text.length < width) text = ` ${text}`;
  return text;
}
function padRight(text, width) {
  text = `${text}`;
  while (text.length < width) text = `${text} `;
  return text;
}

function thereIsASmallFix(current, existing) {
  return (
    thereIsOnlyTheNFromAnMissing(current, existing) ||
    thereIsOnlyOneSMissing(existing, current)
  );
}

function thereIsOnlyTheNFromAnMissing(withAn, withA) {
  return (
    withAn.length > withA.length &&
    (withAn.replaceAll(/An([A-Z])/g, "A$1") === withA ||
      (/^An[A-Z].*/.test(withAn) && /^A[A-Z].*/.test(withA)))
  );
}

function thereIsOnlyOneSMissing(missesS, withS) {
  if (missesS.length >= withS.length) return false;

  let withIndex = 0;
  for (
    let missIndex = 0;
    missIndex < missesS.length;
    missIndex += 1, withIndex += 1
  ) {
    let charWith = withS[withIndex];
    let charMiss = missesS[missIndex];
    if (charWith === charMiss) continue;
    if (charWith !== "s") return false;

    withIndex += 1;
    charWith = withS[withIndex];
    if (charWith !== charMiss) return false;
  }
  return true;
}
