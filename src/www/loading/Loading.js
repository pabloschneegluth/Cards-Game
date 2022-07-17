import { useSelector } from "react-redux";
import { selectIsLoading } from "./loadingSlice";

export function Loading() {
  const loading = useSelector(selectIsLoading);
  if (!loading) return null;

  return (
    <span className="loading" data-testid="loading">
      Loading...
    </span>
  );
}
