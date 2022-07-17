import { useDispatch } from "react-redux";
import { requestDiscardActiveCard } from "./cardSlice";

export function Discard() {
  const dispatch = useDispatch();
  const discard = () => dispatch(requestDiscardActiveCard());

  return (
    <div className="discard" data-testid="discard" onClick={discard}>
      🗑️ Discard
    </div>
  );
}
