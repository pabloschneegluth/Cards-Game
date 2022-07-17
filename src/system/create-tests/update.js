const chalk = require("chalk");
const { writeJavaTestFile } = require("./writeJavaTestFile");
const { writeJavaContextFile } = require("./writeJavaContextFile");
const { verifyPost } = require("./verifyPost");
const { readBlogPost } = require("./readBlogPost");
const { writeJsTestFile } = require("./writeJsTestFile");
const { writeJsContextFile } = require("./writeJsContextFile");
const { registerPost } = require("./posts");
const { writeOriginalCopy } = require("./writeOriginalCopy");
const { debugPost } = require("./debugPost");

async function update(filePath) {
  const post = await readBlogPost(filePath);

  if (post.frontmatter.values.debug === "true") debugPost(post);

  post.failed = !verifyPost(post);
  if (post.failed) return post;
  registerPost(post);

  writeOriginalCopy(post);

  let testWritten = false;
  let contextWritten = false;
  if (post.hasCoder) {
    testWritten = await writeJavaTestFile(post);
    contextWritten = await writeJavaContextFile(post);
    await writeJsTestFile(post);
    await writeJsContextFile(post);
  }

  let action = chalk.inverse(" Updated ");
  if (!post.hasCoder) action = chalk.yellowBright.inverse(" Skipped ");
  if (contextWritten) action = chalk.greenBright.inverse(" Created ");

  const stats = `(${post.contextMethods.length} methods, ${post.testCalls.length} calls)`;

  console.log(
    `[${new Date().toLocaleTimeString("ca")}] ${action} ${filePath} ${stats}` +
      `${testWritten ? ` => ${post.testName}` : ""}` +
      `${contextWritten ? ` => ${post.contextName}` : ""}` +
      `${post.hasCoder ? "" : " (no coder) "}`,
  );

  return post;
}

exports.update = update;
