import chalk from "chalk";
import { readFileSync } from "fs";
import { codeFrameColumns } from "@babel/code-frame";

export function getCodeFrame(frame) {
  let filename, line, column;
  let frameLocation;

  const locationStart = frame.indexOf("(") + 1;
  if (locationStart > 0) {
    const locationEnd = frame.indexOf(")");
    frameLocation = frame.slice(locationStart, locationEnd);

    const frameLocationElements = frameLocation.split(":");
    [filename, line, column] = [
      frameLocationElements[0],
      parseInt(frameLocationElements[1], 10),
      parseInt(frameLocationElements[2], 10),
    ];
  } else {
    frameLocation = frame.split(" ").at(-1);
    const frameParts = frameLocation.split(":");
    [filename, line, column] = [
      frameParts[0],
      parseInt(frameParts[1], 10),
      parseInt(frameParts[2], 10),
    ];
  }

  let rawFileContents = "";
  try {
    rawFileContents = readFileSync(filename, "utf-8");
  } catch {
    return "";
  }

  const codeFrame = codeFrameColumns(
    rawFileContents,
    {
      start: { line, column },
    },
    {
      highlightCode: true,
    },
  );
  return `${chalk.dim(frameLocation)}\n${codeFrame}\n`;
}
