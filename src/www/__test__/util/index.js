import {
  prepareBackendResponses,
  closeBackendResponses,
} from "./backendSimulator";
import { getCodeFrame } from "./getCodeFrame";
import { renderApp } from "./renderApp";
import { verifyPostMd5 } from "./verifyPostMd5";

const realLog = console.log;

export async function runBeforeTestStarts(postId, expectedMd5) {
  await verifyPostMd5(postId, expectedMd5);

  console.log = logWithTest;

  prepareBackendResponses(postId);
  renderApp();
}

export async function runWhenTestSuccessful() {
  await closeBackendResponses();
  console.log = realLog;
}

function logWithTest(...args) {
  const text = args.join(" ");
  if (!text.includes("Error")) return realLog(...args);

  const stack = new Error().stack;
  const frame = stack.split("\n").find((s) => s.includes("_Test.spec."));

  realLog(...args, "\n", getCodeFrame(frame));
}
