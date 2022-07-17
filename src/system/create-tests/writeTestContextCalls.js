function writeTestContextCalls(post, subHandler) {
  const handler = { ...defaultHandler, ...subHandler };
  const debug = post.frontmatter.values.debug === "true";

  const { testCalls } = post;
  const callGroupLines = testCalls.map((call) =>
    writeTestContextCall(post, call, handler, debug),
  );

  if (!debug) {
    let longestLine = Math.max(
      ...callGroupLines.flat(Infinity).map((l) => l.length),
    );
    callGroupLines.forEach((callLines, groupIndex) => {
      let invokeIndex = callLines.length - 1;
      while (callLines[invokeIndex].length < longestLine)
        callLines[invokeIndex] += " ";
      callLines[invokeIndex] += ` ${handler.comment(
        testCalls[groupIndex].text.trim(),
      )}`;
    });
  }

  return callGroupLines;
}
exports.writeTestContextCalls = writeTestContextCalls;

function writeTestContextCall(post, call, handler, debug) {
  let result = [];

  if (call.isComment && call.text.startsWith("## ")) result.push("");
  if (call.isComment) result.push(handler.indent + handler.comment(call.text));

  if (debug) result.push(handler.indent + handler.debug(call.text));

  if (call.isMethod) result.push(handler.indent + handler.invoke(call));

  if (debug) result.push("");

  return result;
}

const defaultHandler = {
  indent: "",
  comment(text) {
    return `// ${text}`;
  },
  invoke(call) {
    return `context.${call.name}(${call.arguments
      .map((a) => a.value)
      .join(", ")});`;
  },
};
