import { useDispatch } from "react-redux";
import { requestEndMoon, requestGame } from "./gameSlice";

export function GameHeader() {
  const dispatch = useDispatch();
  const endMoon = () => dispatch(requestEndMoon());
  const reload = () => dispatch(requestGame());

  return (
    <div className="game-header">
      <button onClick={endMoon}>End Moon</button>
      <button onClick={reload}>Reload</button>
    </div>
  );
}
