const { reportPostError } = require("./reportPostError");

function verifyNonEmptyPost(post) {
  if (post.lines.some((l) => l)) return true;

  // post.id: 2022-07-30_monaco_editor_two
  // title: Monaco Editor Two
  const title = post.id
    .slice(11)
    .replace(/_/g, " ")
    .replace(/\b\w/g, (l) => l.toUpperCase());

  reportPostError(post, 1, [
    `is empty.`,
    ``,
    `Please, start writting the content for this post.`,
    `Remember that the following parts are necessary:`,
    `- the frontmatter (the lines between the ---),`,
    `- the writer's name,`,
    `- the sub-package's name,`,
    `- the title (the line starting with #),`,
    `- some engaging description,`,
    `- an example with instructions to replicate it,`,
    ``,
    `As initial example you can copy-paste:`,
    ``,
    `---`,
    `writer: !youralias`,
    `package: !chooseone`,
    `---`,
    `# ${title}`,
    ``,
    `This is an engaging description of your feature.`,
    ``,
    `## Example`,
    ``,
    `This is an example of how to use your feature.`,
    ``,
    ` * Given there are 3 "Some" cards.`,
    ` * End the current moon.`,
    ` * There should be 3 "Some" cards.`,
    ``,
    `_Thanks for the read!_.`,
    `---`,
    ``,
  ]);
}
exports.verifyNonEmptyPost = verifyNonEmptyPost;
