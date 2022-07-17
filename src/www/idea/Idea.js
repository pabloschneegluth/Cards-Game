import { useDispatch, useSelector } from "react-redux";
import { selectIdea, requestDrawIdea } from "./ideaSlice";

export function Idea({ ideaName }) {
  const idea = useSelector((s) => selectIdea(s, ideaName));
  const dispatch = useDispatch();
  const drawIdea = () => dispatch(requestDrawIdea(idea.id));

  return (
    <div
      className="idea with-details"
      data-testid="idea"
      data-ideaname={idea.name}
      onClick={drawIdea}
    >
      {idea.name} ({idea.level}+{idea.xp})
      <div className="details">
        <div>
          <span data-testid="level">Level {idea.level}</span>,
          <span data-testid="xp"> {idea.xp}xp</span>
        </div>
        <br />
        Requires:
        {idea.tagRequirements.map((tagRequirement) => (
          <IdeaTagRequirement
            key={tagRequirement.tagName}
            tagRequirement={tagRequirement}
          />
        ))}
        <br />
        {idea.cardRewards.length > 0 && (
          <>
            May create cards:
            <ul>
              {idea.cardRewards.map((cardReward) => (
                <li key={cardReward} data-testid="maycreatecard">
                  {cardReward}
                </li>
              ))}
            </ul>
          </>
        )}
      </div>
    </div>
  );
}

function IdeaTagRequirement({ tagRequirement }) {
  return (
    <div data-testid="ideaTagRequirement">
      {tagRequirement.cardCount} x {tagRequirement.tagValue}
      {" of "}
      {tagRequirement.tagName}
    </div>
  );
}
