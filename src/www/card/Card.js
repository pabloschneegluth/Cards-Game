import { useDispatch, useSelector } from "react-redux";
import { requestMoveActiveCard } from "../stack/stackSlice";
import { selectCard, activateCard, selectActiveCardId } from "./cardSlice";

export function Card({ cardId, index }) {
  const card = useSelector((s) => selectCard(s, cardId));
  const activeCardId = useSelector(selectActiveCardId);
  const isAnyActive = !!activeCardId;
  const isActive = cardId === activeCardId;
  const hasProgress = card.maxProgress > 1;

  const dispatch = useDispatch();
  const activateOrMove = (ev) => {
    ev.stopPropagation();
    if (!isAnyActive) dispatch(activateCard(cardId));
    else dispatch(requestMoveActiveCard(card.position, index + 1));
  };

  const image = `cards/${card.name.toLowerCase().replaceAll(/\s/g, "-")}.png`;

  return (
    <div
      className={`card with-details ${hasProgress ? "has-progress" : ""} ${
        isActive ? "active" : ""
      } ${card.looksLike}`}
      data-testid="card"
      data-cardid={card.id}
      data-cardname={card.name}
      data-zindex={card.zindex}
      onClick={activateOrMove}
    >
      {hasProgress && (
        <div className="card-progress" data-testid="cardprogress">
          {card.progress} of {card.maxProgress}
        </div>
      )}
      <img src={image} alt="a card drawing" draggable={false} />
      <div className="card-name">{card.name}</div>
      <div className="details">
        Tags:
        <br />
        {Object.entries(card.tags).map(([tagName, tag]) => (
          <div key={tagName} data-tagname={tagName}>
            - {tagName}: {tag.value}
          </div>
        ))}
        <br />
        Terms:
        {Object.entries(card.description).map(([term, text]) => (
          <CardTermDescription key={term} term={term} text={text} />
        ))}
      </div>
    </div>
  );
}

function CardTermDescription({ term, text }) {
  return (
    <div data-carddescriptionterm={term}>
      {term} is {text}
    </div>
  );
}
