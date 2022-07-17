import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Discard } from "../card/Discard";
import { Idea } from "../idea/Idea";
import { selectAllIdeaNames } from "../idea/ideaSlice";
import { Stack } from "../stack/Stack";
import { selectAllStackPositions } from "../stack/stackSlice";
import { GameHeader } from "./GameHeader";
import { requestGame } from "./gameSlice";

export function GamePage() {
  const stackPositions = useSelector(selectAllStackPositions);
  const ideaNames = useSelector(selectAllIdeaNames);

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(requestGame());
  }, [dispatch]);

  return (
    <div>
      <GameHeader />
      <div className="idea-sidebar">
        <Discard />
        {ideaNames.map((name) => (
          <Idea key={name} ideaName={name} />
        ))}
      </div>
      <div className="table">
        {stackPositions.map((position) => (
          <Stack key={position} position={position} />
        ))}
      </div>
    </div>
  );
}
