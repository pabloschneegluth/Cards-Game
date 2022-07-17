import chalk from "chalk";
import { join } from "./join";

export function throwBackendSimulatorError(backendSimulator, ...messages) {
  const firstJumpIndex = messages.findIndex((m) => !m.endsWith(" "));
  const message = messages.slice(0, firstJumpIndex + 1).join("");
  const additional = messages.slice(firstJumpIndex + 1);

  const output = magentize(
    please(
      expectize(
        join(
          `${chalk.bold.magenta.inverse(" BACKEND API CALL ERROR ")} `,
          `${chalk.bold.magenta(`${backendSimulator.postId}.md`)}`,
          chalk.magenta(message),
          explainSituation(backendSimulator),
          additional.length && join(``, additional),
          `\n`,
        ),
      ),
    ),
  );

  process.stderr.write(chalk.gray(output));

  throw new Error(
    `Error: ${backendSimulator.postId} backend apiCalls failed. ${message}`,
  );
}

function expectize(output) {
  return output
    .split("\n")
    .map((line) => {
      const match = line.match(/^-(.+:).+$/);
      if (!match) return line;
      const [, key] = match;
      if (key.includes("expect")) return chalk.green(line);
      if (key.includes("actual")) return chalk.red(line);
      return line;
    })
    .join("\n");
}

function magentize(output) {
  return output
    .split("\n")
    .map((line) => {
      const match = line.match(/^([A-Z-].+:)(.*)$/);
      if (!match) return line;
      return chalk.magenta(match[1]) + match[2];
    })
    .join("\n");
}

function please(output) {
  return output
    .split("\n")
    .map((line) => {
      if (!line.startsWith("Please")) return line;
      return chalk.magenta(line);
    })
    .join("\n");
}

function explainSituation(backendSimulator) {
  let result = [
    `\nThe apiCall simulation between backend and frontend is recorded at:`,
  ];
  result.push(chalk.black.underline(backendSimulator.filePath));
  result.push(`\nThe recorded apiCalls are:`);

  let apiCallIndex = 0;
  let currentApiCallIndex = backendSimulator.nextApiCallIndex - 1;
  while (apiCallIndex < currentApiCallIndex) {
    result.push(
      chalk.green(`   ${explainLineApiCall(backendSimulator, apiCallIndex)}`),
    );
    apiCallIndex += 1;
  }

  if (apiCallIndex < backendSimulator.apiCalls.length)
    result.push(
      chalk.red(`** ${explainLineApiCall(backendSimulator, apiCallIndex)}`),
    );
  apiCallIndex += 1;

  while (apiCallIndex < backendSimulator.apiCalls.length) {
    result.push(
      chalk.grey(`   ${explainLineApiCall(backendSimulator, apiCallIndex)}`),
    );
    apiCallIndex += 1;
  }
  if (backendSimulator.nextApiCallIndex < backendSimulator.apiCalls.length) {
    result.push(`\nThe next expected apiCall is:`);
    result.push(
      explainApiCall(
        backendSimulator.apiCalls[backendSimulator.nextApiCallIndex],
      ),
    );
  }

  return result;
}

function explainLineApiCall(backendSimulator, index) {
  const apiCall = backendSimulator.apiCalls[index];
  return `[${pad(index + 1)}]  ${apiCall.request.method} ${
    apiCall.request.url
  }`;
}
export function explainRequest(apiCall) {
  return [
    `- method: "${apiCall.method}"`,
    `- url   : "${apiCall.url}"`,
    apiCall.body
      ? `- body  : ${JSON.stringify(apiCall.body, null, 2)}`
      : `- body  : null`,
  ];
}

function explainApiCall(apiCall) {
  return [
    `- request method : "${apiCall.request.method}"`,
    `- request url    : "${apiCall.request.url}"`,
    apiCall.body
      ? `- request body   : ${JSON.stringify(apiCall.body, null, 2)}`
      : `- request body   : null`,
    `- response body  : ${JSON.stringify(apiCall.response.body, null, 2)}`,
  ];
}

function pad(n) {
  let o = `${n}`;
  while (o.length < 3) o = ` ${o}`;
  return o;
}
