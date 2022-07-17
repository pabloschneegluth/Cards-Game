const { isAuthorValid, getAuthorUsernames } = require("./authors");
const { reportPostError } = require("./reportPostError");

const mandatoryKeys = ["writer", "package"];
const validKeys = [...mandatoryKeys, "coder"];
const keysWithAuthors = ["writer", "coder"];

if (!process.env.CI !== "1") validKeys.push("debug");

function verifyPostFrontmatterSemantics(post) {
  return (
    verifyPostFrontmatterKeys(post) &&
    verifyPostFrontmatterMandatoryKeys(post) &&
    verifyPostAuthors(post) &&
    verifyPostFrontmatterPackageKey(post) &&
    verifyPostFrontmatterPackageKeyNotComDrpicoxGame(post) &&
    verifyPostWriterAndCodeAreDifferent(post)
  );
}

exports.verifyPostFrontmatterSemantics = verifyPostFrontmatterSemantics;

function verifyPostFrontmatterKeys(post) {
  const actualKeys = Object.keys(post.frontmatter.values);
  const wrongKey = actualKeys.find((key) => !validKeys.includes(key));
  if (!wrongKey) return true;

  const wrongLine = post.frontmatter.valuesLines[wrongKey];
  reportPostError(post, wrongLine, [
    `fromatter key "${wrongKey}" is not valid.`,
    `- expected keys: "${validKeys.join('", "')}"`,
    `- actual key   : "${wrongKey}"`,
    `Please, fix the spelling or remove this key from the frontmatter.`,
  ]);

  return false;
}

function verifyPostFrontmatterMandatoryKeys(post) {
  const actualKeys = Object.keys(post.frontmatter.values);
  const missingKey = mandatoryKeys.find((key) => !actualKeys.includes(key));
  if (!missingKey) return true;

  reportPostError(post, post.frontmatter.closingIndex + 1, [
    `fromatter key "${missingKey}" is required but it is missing.`,
    `- mandatory keys: "${mandatoryKeys.join('", "')}"`,
    `- missing key   : "${missingKey}"`,
    `Please, add the missing key to the frontmatter.`,
  ]);

  return false;
}

function verifyPostAuthors(post) {
  const actualKeys = Object.keys(post.frontmatter.values);
  const existingKeysWithAuthors = actualKeys.filter((key) =>
    keysWithAuthors.includes(key),
  );
  const wrongKey = existingKeysWithAuthors.find(
    (key) => !isAuthorValid(post.frontmatter.values[key]),
  );
  if (!wrongKey) return true;

  const wrongAuthor = post.frontmatter.values[wrongKey];
  const wrongLine = post.frontmatter.valuesLines[wrongKey];
  reportPostError(post, wrongLine, [
    `frontmatter key "${wrongKey}" username author name "${wrongAuthor}" was not found at authors.properties.`,
    `- valid author names    : "${getAuthorUsernames().join('", "')}"`,
    `- actual author name    : "${wrongAuthor}"`,
    `- actual frontmatter key: "${wrongKey}"`,
    `Please, verify that the name is correct, and `,
    `if you think that the author is missing, add it to authors.properties, `,
    `or make sure that it is in the right "username=Author Name" line format.`,
  ]);

  return false;
}

const packageRegExp = /^[a-z][a-zA-Z0-9]*(\.[a-z][a-zA-Z0-9]*)*$/;
function verifyPostFrontmatterPackageKey(post) {
  const { package: pkg } = post.frontmatter.values;
  if (!pkg) return true;
  if (packageRegExp.test(pkg)) return true;

  const wrongLine = post.frontmatter.valuesLines.package;
  reportPostError(post, wrongLine, [
    `frontmatter "package" or is either empty or has satisfies this format ${packageRegExp.source} but it was "${pkg}". `,
    `- actual package value            : "${pkg}"`,
    `- example of valid package value  : "cards"`,
    `- example of valid package value  : "ideas.places"`,
    `- example of valid package value  : "someStrange3.packageName"`,
    `- example of invalid package value: "Caps"`,
    `- example of invalid package value: "12Number"`,
    `- example of invalid package value: "with/slash"`,
    `Please, remove or fix the package value.`,
  ]);

  return false;
}

function verifyPostFrontmatterPackageKeyNotComDrpicoxGame(post) {
  const { package: pkg } = post.frontmatter.values;
  if (!pkg) return true;
  if (!pkg.startsWith("com.drpicox.game") && !pkg.includes("drpicox"))
    return true;

  const wrongLine = post.frontmatter.valuesLines.package;
  const recommendedPackage = pkg
    .replace("com.drpicox.game.", "")
    .replace("drpicox.", "");

  reportPostError(post, wrongLine, [
    `frontmatter "package" cannot start with "com.drpicox.game" or include "drpicox", but it was "${pkg}". `,
    `Please make sure that you only include the relative package name from com.drpicox.`,
    `- instead of  : "package: ${pkg}"`,
    `- you may want: "package: ${recommendedPackage}"`,
    `Examples:`,
    `- example of invalid package value: "com.drpicox.game.ideas.places"`,
    `- example of valid package value  : "ideas.places"`,
    `Please, remove or fix the package value.`,
  ]);

  return false;
}

function verifyPostWriterAndCodeAreDifferent(post) {
  const { writer, coder } = post.frontmatter.values;
  if (writer !== coder || coder === "drpicox") return true;

  const wrongLine = post.frontmatter.valuesLines.coder;
  const acceptableCoders = getAuthorUsernames().filter(
    (coder) => coder !== writer,
  );
  reportPostError(post, wrongLine, [
    `frontmatter "writer" and "coder" cannot be the same, but both were "${writer}". `,
    `The writer of a post cannot be the coder who implements the post.`,
    `- actual writer value : "${writer}"`,
    `- actual coder value  : "${coder}"`,
    `- expected coder value: not ${coder}`,
    `- acceptable coders   : "${acceptableCoders.join('", "')}"`,
    `Please, fix it if you are not "${writer}" or look for another post to solve.`,
  ]);

  return false;
}
