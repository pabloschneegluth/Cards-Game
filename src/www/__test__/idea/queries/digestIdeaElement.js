import { getByTestId, queryAllByTestId, screen } from "@testing-library/dom";
import { createDigestFunction } from "../../util/digest";

export const digestIdeaElement = createDigestFunction("idea", (ideaElement) => {
  const ideaDigest = {
    name: ideaElement.dataset.ideaname,
    level: findNumber(getByTestId(ideaElement, "level")),
    xp: findNumber(getByTestId(ideaElement, "xp")),
    mayCreateCards: getMayCreateCards(ideaElement),
    tagRequirements: getTagRequirements(ideaElement),
  };

  return ideaDigest;
});

function findNumber(element) {
  return +element.textContent.match(/\d+/)[0];
}

function getMayCreateCards(ideaElement) {
  const mayCreateCardsElement = queryAllByTestId(ideaElement, "maycreatecard");
  return [...mayCreateCardsElement].map((child) => child.textContent);
}

function getTagRequirements(ideaElement) {
  const tagRequirementElements = queryAllByTestId(
    ideaElement,
    "ideaTagRequirement",
  );

  return Object.fromEntries(
    [...tagRequirementElements].map((child) => {
      const tagRequirement = getTagRequirement(child);
      return [tagRequirement.tagName, tagRequirement];
    }),
  );
}

function getTagRequirement(tagRequirementElement) {
  const [, cardCount, tagValue, tagName] =
    tagRequirementElement.textContent.match(/(\d+) x (\d+) of (.+)/);

  return { cardCount: +cardCount, tagValue: +tagValue, tagName };
}
