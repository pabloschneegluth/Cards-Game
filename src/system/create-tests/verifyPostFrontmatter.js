const { reportPostError } = require("./reportPostError");

function verifyPostFrontmatter(post) {
  return (
    verifyPostFrontmatterOpening(post) &&
    verifyPostFrontmatterClosing(post) &&
    verifyPostFrontmatterValues(post)
  );
}
exports.verifyPostFrontmatter = verifyPostFrontmatter;
function verifyPostFrontmatterOpening(post) {
  if (post.frontmatter.isOpeningOk) return true;

  reportPostError(post, 1, [
    `should begin with frontmatter. `,
    `The first line should begin with three dashes "---".`,
    `- expected first line: "---"`,
    `- actual first line  : "${post.lines[0].trim()}"`,
    `Please, add the line with three dashes in the beggining of the post.`,
  ]);

  return false;
}
function verifyPostFrontmatterClosing(post) {
  if (post.frontmatter.isClosingOk) return true;

  reportPostError(post, post.lines.length + 1, [
    `frontmatter should be closed. `,
    `The closing is a line with three dashes "---" like the frontmatter opening (the first line).`,
    `The frontmatter would look like this:`,
    `  ---`,
    `  writer: drpicox`,
    `  ---`,
    `The last line with the three dashes is the frontmatter closing.`,
    `Please, add the closing three dashes line of the frontmatter to the post.`,
  ]);

  return false;
}
function verifyPostFrontmatterValues(post) {
  if (post.frontmatter.areValuesOk) return true;

  const [error] = post.frontmatter.wrongValuesLines;
  reportPostError(post, error.lineNumber, [
    `frontmatter values should be in the right format. `,
    `The frontmatter values lines should begin with a variable name, the colon ":", and the value.`,
    `- expected example      : "writer: drpicox"`,
    `- actual key:value found: "${error.lineText}"`,
    `Please, fix the key:value or remove the line.`,
  ]);

  return false;
}
