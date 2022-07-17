---
writer: drpicox
package: idea
coder: drpicox
---
# I have an idea to take a stroll in the wood and find random things

Until now everything was predictible, like 1 + 1 = 2,
but now we are introducing random events.

So, what about do a nice walk in the woods and find random things?

## Walk in the woods idea

So, why not get the idea to make a random walk through the woods?

 * Enter the game.
 * There should be the "Harvest Idea" idea.
 * The "Woods Stroll Idea" should have level 1 and 0 XP.

But when you start using them, the XP in the "Harvest Idea" idea should increase.
If we look to the idea,we can see some more information:

 * The "Woods Stroll Idea" may create a "Berry" card.
 * The "Woods Stroll Idea" may create a "Apple" card.
 * The "Woods Stroll Idea" idea should require 1 card with at least 1 in "Worker" tag.

So, lets put this in movement.

## Finding things

### Finding things in the woods

We can find any of the following things in the woods:

| $CardName |
|-----------|
| Berry     |
| Apple     |

 * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
 * Given that the odds are that we will get a "$CardName" from the "Woods Stroll Idea" card.
 * End the current moon. 
 * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "$CardName" cards.

### In the level 1

That was because we were in the level one, but what if we go to the level 2?
Before that, we knew that we had no reward with of a Villager card:

* Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
* Given there is the "Harvest Idea" idea at level 1 and 0 XP.
* The "Woods Stroll Idea" may not create a "Villager" card.

### In a higher level

But at level 2, you may also find Villagers in the woods:

| $CardName |
|-----------|
| Berry     |
| Apple     |
| Villager  |

We should see the $CardName as possible reward:

 * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
 * Given there is the "Woods Stroll Idea" idea at level 2 and 0 XP.
 * The "Woods Stroll Idea" may create a "$CardName" card.

But it should also possible to receive it as a reward:

 * Given that the odds are that we will get a "$CardName" from the "Woods Stroll Idea" card.
 * End the current moon.
 * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager", and 1 "$CardName" cards.

