import { useNavigate } from "react-router";

export function HomePage() {
  const navigate = useNavigate();
  const enterTheGame = () => navigate("/game");

  return (
    <div>
      <h1>Welcome</h1>
      <button onClick={enterTheGame}>Enter the Game</button>
    </div>
  );
}
