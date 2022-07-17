---
writer: drpicox
package: idea
debug: true
---
# Ideas Have Levels

The more you practice an idea, more skilled you become.
And more skills, more things may happen, and more ideas may appear.
So get ready to accumulate experience points and increase levels!

## Increasing XP

When the game begins, the XP in any idea is zero.

 * Enter the game.
 * There should be the "Harvest Idea" idea.
 * The "Harvest Idea" should have level 1 and 0 XP.

But when you start using them, the XP in the "Harvest Idea" idea should increase.

 * Draw a card from the "Harvest Idea" idea.
 * Move the "Harvest Idea" card to its own stack.
 * Move the "Villager" card on top of the "Harvest Idea" card.
 * Move the "Berry Bush" card on top of the "Villager" card.
 * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * There should be 1 "Berry" cards.

If we end the moon, we will se how the XP would increase.

 * The "Harvest Idea" should have level 1 and 0 XP.
 * End the current moon.
 * The "Harvest Idea" should have level 1 and 1 XP.
 * There should be 2 "Berry" cards.

But the level still remains. We need to gain more experience in this!

### Gaining several XP at once

We won only one XP because there was only one use of the idea.
But what if we create two berry production piles?

 * Given a new game.
 * Given there is the "Harvest Idea" idea.
 * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * The "Harvest Idea" should have level 1 and 0 XP.
 * End the current moon.
 * The "Harvest Idea" should have level 1 and 2 XP.

We earned two XP because we used the idea twice.

## Increasing level

Each level has a different barrier, once you achieve the
barrier for 10 XP, then you go to the level 2.

 * Given a new game.
 * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
 * Given there are 1 "Berry" cards.
 * Given there are 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
 * End the current moon.
 * The "Harvest Idea" should have level 2 and 0 XP.
 * There should be 3 "Berry" cards.

We finally did it! We have reached the next level.
And you saw it, now we have three berries!
We produce three berries instead of two. That is great!

### Increasing more levels

But, there are more levels? Yes! 
Let's see it.

| Previous Level | Required XP | Next Level | Next Berries |
|---------------:|------------:|-----------:|-------------:|
|              1 |           9 |          2 |            3 |
|              2 |          19 |          3 |            4 |
|              3 |          29 |          4 |            5 |
|              4 |          39 |          5 |            6 |
|              5 |          49 |          6 |            7 |

 * Given a new game with 1 "Berry Bush" production stack.
 * Given there is the "Harvest Idea" idea at level _Previous Level_ and _Required XP_ XP.
 * End the current moon.
 * The "Harvest Idea" should have level _Next Level_ and 0 XP.
 * There should be _Next Berries_ "Berry" cards.

It increased the level, and now we produce four berries.
Let's see a few more:

 * Given a new game with 1 "Berry" and 1 "Berry Bush" production stack.
 * Given there is the "Harvest Idea" idea at level 3 and 29 XP.
 * End the current moon.
 * The "Harvest Idea" should have level 4 and 0 XP.
 * There should be 5 "Berry" cards.

And...

 * Given a new game with 1 "Berry" and 1 "Berry Bush" production stack.
 * Given there is the "Harvest Idea" idea at level 4 and 39 XP.
 * End the current moon.
 * The "Harvest Idea" should have level 5 and 0 XP.
 * There should be 6 "Berry" cards.

And so on.

### XP are not lost

When we gain several XP, we keep the remaining points in the next level.
 
 * Given a new game with 3 "Berry Bush" production stack.
 * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
 * End the current moon.
 * The "Harvest Idea" should have level 2 and 2 XP.

We gain the XP to go to the next level, and we gain the extra two XP for the other two stacks.

## New ideas from existing ideas

Often, practicing known ideas, new ideas are created.
For example, if you would be expected that after observing how we can harvest berries,
we realize that "Berry Bush" grow from "Berry". 
So probably we can learn how to plant by harvesting.

 * Given a new game with 1 "Berry Bush" production stack.
 * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
 * There should be no "Plant Seed" idea.
 * End the current moon.
 * The "Harvest Idea" should have level 2 and 0 XP.
 * There should be the "Plant Seed" idea.
 * The "Plant Seed" should have level 1 and 0 XP.

### Do not lose old XP gaining again

You can learn the same idea from two different places, but
do not worry, the XP points are kept. 

Let's see it:

 * Given a new game with 1 "Berry Bush" production stack.
 * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
 * Given there is the "Seed Idea" idea at level 3 and 12 XP.
 * End the current moon.
 * The "Harvest Idea" should have level 2 and 0 XP.
 * The "Seed Idea" should have level 3 and 12 XP.

Your idea level and XP are not lost.

## Ideas may take several moons.

But not all ideas run at the same time, some ideas may take more time to develop.
For example, harvesting is faster than seeding.

 * Given a new game.
 * Given there is the "Seed Idea" idea at level 1 and 0 XP.
 * The "Seed Idea" idea should require the sum of 1 in "Seed" tag cards.
 * The "Seed Idea" idea should require the sum of 1 in "Worker" tag cards.
 * Given there are 1 stacks of 1 "Seed Idea", 1 "Villager", and 1 "Berry" cards.
 * Given there are 0 "Berry Bush" cards.
 * End the current moon.
 * The "Seed Idea" should have level 1 and 0 XP.
 * There should be 0 "Berry Bush" cards.

Everything seems to be the same. Why? Let's look to the progress.

 * The "Seed Idea" card progress should be 1 of 5.

That means that we have used 1 moon, but we still need four more moons to complete the idea.

 * End the current moon.
 * The "Seed Idea" card progress should be 2 of 5.
 * End the current moon.
 * The "Seed Idea" card progress should be 3 of 5.
 * End the current moon.
 * The "Seed Idea" card progress should be 4 of 5.

And not, it is almost done!

 * The "Seed Idea" should have level 1 and 0 XP.
 * There should be 0 "Berry Bush" cards.
 * The "Seed Idea" card progress should be 4 of 5.
 * End the current moon.
 * The "Seed Idea" card progress should be 0 of 5.
 * The "Seed Idea" should have level 1 and 1 XP.
 * There should be 1 "Berry Bush" cards.

Finally. After 5 exhausting moons working, we did it.

