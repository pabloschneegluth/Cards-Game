import { queryByTestId } from "@testing-library/dom";
import { createDigestFunction } from "../../util/digest";

export const digestCardElement = createDigestFunction("card", (cardElement) => {
  const cardDigest = {
    id: cardElement.dataset.cardid,
    name: cardElement.dataset.cardname,
    zindex: +cardElement.dataset.zindex,
    terms: getTerms(cardElement),
    tags: getTags(cardElement),
    ...getProgressAndMaxProgress(cardElement),

    getTag: (tagName) => cardDigest.tags[tagName] ?? 0,
  };

  return cardDigest;
});

function getTerms(cardElement) {
  return Object.fromEntries(
    [...cardElement.querySelectorAll("[data-carddescriptionterm]")].map(
      (element) => [
        element.dataset.carddescriptionterm,
        element.textContent.split(" ").slice(2).join(" "),
      ],
    ),
  );
}

function getTags(cardElement) {
  return Object.fromEntries(
    [...cardElement.querySelectorAll("[data-tagname]")].map((element) => [
      element.dataset.tagname,
      getNumber(element.textContent),
    ]),
  );
}

function getProgressAndMaxProgress(cardElement) {
  const progressElement = queryByTestId(cardElement, "cardprogress");
  if (!progressElement) return {};

  const [progressStr, maxProgressStr] =
    progressElement.textContent.split(" of ");
  const progress = +progressStr;
  const maxProgress = +maxProgressStr;

  return { progress, maxProgress };
}

function getNumber(text) {
  return +text.match(/\d+/)[0];
}
