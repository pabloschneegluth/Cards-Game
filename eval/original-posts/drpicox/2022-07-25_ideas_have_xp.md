---
writer: drpicox
package: idea
debug: true
---
# Ideas Have XP

The more you practice an idea, more skilled you become.
And more skills, more things may happen, and more ideas may appear.
So get ready to accumulate experience points.

## Increasing XP

When the game begins, the XP in any idea is zero.

 * Enter the game.
 * There should be the "Harvest Idea" idea.
 * The "Harvest Idea" should have 0 XP.

But when you start using them, the XP in the "Harvest Idea" idea should increase.

 * Draw a card from the "Harvest Idea" idea.
 * Move the "Harvest Idea" card to its own stack.
 * Move the "Villager" card on top of the "Harvest Idea" card.
 * Move the "Berry Bush" card on top of the "Villager" card.
 * There should be 1 stack of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * There should be 1 "Berry" cards.

If we end the moon, we will se how the XP would increase.

 * The "Harvest Idea" should have 0 XP.
 * End the current moon.
 * The "Harvest Idea" should have 1 XP.

We gain some experience in this!

### Gaining several XP at once

We won only one XP because there was only one use of the idea.
But what if we create two piles?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * The "Harvest Idea" should have 0 XP.
 * End the current moon.
 * The "Harvest Idea" should have 2 XP.

We earned two XP because we used the idea twice. But we do not need two stacks for that,
we can do it in the same stack:

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * The "Harvest Idea" should have 0 XP.
 * End the current moon.
 * The "Harvest Idea" should have 2 XP.


So we have one new berries. But wait, did the villager eat its food? Yes!
So in fact harvest gets two berries.

### Order has some importance

What is important, is that all three cards are together, so, if we change the order,
it should work.

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Berry Bush", and 1 "Villager" cards.
 * End the current moon.
 * There should be 2 "Berry" cards.

Unless, the idea is not the bottom card. Then it does not work.

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
 * End the current moon.
 * There should be 0 "Berry" cards.

### Using ideas multiple times

The ides can be used several times, so:

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 1 stack of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 1 stack of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * There should be 3 "Berry" cards.

So, after two moons, we have accumulated 2 more berries.

### Using the same idea twice

Because we can draw twice an idea, we can create two stacks. So:

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 2 "Berry" cards.
 * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 4 "Berry" cards.

We had two villagers eating, so they eat 2 berries, but they produced four berries,
two new berries for each villager.

### Eating comes last

Well, villagers are eager workers, they finish their chores before eating.
So, if you do not have food enough, but you have a bush, you can always get food for them.

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 0 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 1 "Villager" cards.
 * There should be 1 "Berry" cards.

So the villager survived, and eat the berry that was produced. Uf.

### Card Substitutes

Ok, we know that "Villager" can harvest, but it makes reasonable that other cards
may be also likely to harvest, like "Militia" (eats two berries):

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 2 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Militia", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 2 "Berry" cards.

So "Militia" works like "Villager". And what about other cards? What about
"Apple Tree"?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Apple" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Apple Tree" cards.
 * End the current moon.
 * There should be 2 "Apple" cards.

And what about mixing them all?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Apple" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Militia", and 1 "Apple Tree" cards.
 * End the current moon.
 * There should be 2 "Apple" cards.

What? An Apple? Yes! Apple trees do Apples, not Berries.

### Idea cards at bottom

The idea cards must be at the bottom, otherwise, they do not work:

  * Given a new game.
  * Given there is the "Harvest Idea" idea.
  * Given there are 1 "Berry" cards.
  * Given there are 1 stacks of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
  * End the current moon.
  * There should be 0 "Berry" cards.

It did not work because the idea was not at the bottom.

### More cards than necessary

What if we add more cards than necessary?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Corpse", 1 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 2 "Berry" cards.

One card has nothing to do with the idea, but it still works.
But, what happens if we have more "Fruit Plant" cards than necessary?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 2 "Berry Bush" cards.
 * End the current moon.
 * There should be 2 "Berry" cards.

It seems that nothing changed, but, what if we add more workers?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 2 "Villager", and 2 "Berry Bush" cards.
 * End the current moon.
 * There should be 3 "Berry" cards.

Now both workers harvest fruits. But, what if we have more workers than fruit plants?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 2 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 1 "Berry" cards.

The second worker did not worked, and eat its berry.

### Mixing kinds of fruits

We have seen that we can have more cards than necessary,
but what if we want to mix "Berry Bushes" and "Apple Trees"?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 0 "Apple" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", 1 "Berry Bush", and 1 "Apple Tree" cards.
 * End the current moon.
 * There should be 2 "Berry" cards.
 * There should be 0 "Apple" cards.

The fruit card from the bottom is the "Berry Bush", so we get two "Berry" cards, but no new "Apple" cards.
But, if we put the "Apple Tree" card below:

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 0 "Berry" cards.
 * Given there are 1 "Apple" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", 1 "Apple Tree", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 0 "Berry" cards.
 * There should be 2 "Apple" cards.

So we have an "Apple".

But... what if we have two workers?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 1 "Berry" cards.
 * Given there are 1 "Apple" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 2 "Villager", 1 "Apple Tree", and 1 "Berry Bush" cards.
 * End the current moon.
 * There should be 3 "Berry" cards.
 * There should be 2 "Apple" cards.

Both "Villager" have worked, and now both "Fruit Plant" cards have produced fruits.

## XP...
