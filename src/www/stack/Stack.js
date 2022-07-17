import { useDispatch, useSelector } from "react-redux";
import { Card } from "../card/Card";
import { requestMoveActiveCard, selectStack } from "./stackSlice";

export function Stack({ position }) {
  const stack = useSelector((s) => selectStack(s, position));
  const dispatch = useDispatch();
  const move = () => dispatch(requestMoveActiveCard(position, 0));

  return (
    <div
      className={`stack stack-${stack.position}`}
      data-testid="stack"
      data-stackposition={stack.position}
      onClick={move}
    >
      {stack.cardIds.map((cardId, index) => (
        <Card key={cardId} cardId={cardId} index={index} />
      ))}
    </div>
  );
}
