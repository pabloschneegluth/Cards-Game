import { readFile } from "fs/promises";
import md5 from "md5";
import path from "path";

export async function verifyPostMd5(postId, expectedMd5) {
  const filePath = path.join(
    __dirname,
    "..",
    "..",
    "..",
    "main",
    "resources",
    "blog",
    `${postId}.md`
  );

  const fileContent = await readFile(filePath);
  const actualMd5 = md5(fileContent);
  if (expectedMd5 !== actualMd5) {
    throw new Error(
      [
        "Post '" + postId + ".md' has changed and the MD5 does not match.",
        "expected md5: " + expectedMd5 + "",
        "actual md5  : " + actualMd5 + "",
        "This error raises probably because you have modified the blogpost but you do not have updated the test. Please, verify that:",
        '- Verify that you have "yarn create-tests" running,',
        "- Verify that you not changed the " + postId + ".md contents,",
        "- In a Unix environment you can run the command md5 " +
          postId +
          ".md and verify that the result matches.",
        "Please, verify that you have the yarn create-tests running correctly.",
      ].join("\n")
    );
  }
}
