---
writer: drpicox
coder: drpicox
package: tag.food
---
# Villagers Eat Food!

We have seen that we have villagers, and we have berries.
It turns out that villagers need food to survive. If not, they die.

Let's see how it goes.

## Berries are food

If you look carefully to the cards, you will
see that it has several properties.
For example, berries are food.

 * Given we have entered into a new game.
 * The "Berry" card should have 1 in "Food" tag.

And villagers eats.

 * The "Villager" card should have 1 in "Eats" tag.

## Moons

The game plays with moon. 
Each moon is a period of time.
You can reorganize cards whenever your want.
But when you have finish, you have to end the moon.
When the moon ends, the rules applies and the magic begin.

 * End the current moon.

And look to the results:

 * There should be 0 "Berry" cards.
 * There should be 1 "Villager" cards.
 * There should be 1 "Berry Bush" cards.

The villager has eaten the berry, and there should be no 
more berries available.

## Starving

Villagers need to eat. 
If they do not eat, they starve to dead.

If we make one more moon pass, 
and if we do not have food for the villagers,
they die.

 * End the current moon.
 * There should be 0 "Berry" cards.
 * There should be 0 "Villager" cards.
 * There should be 1 "Berry Bush" cards.

Wait, what is that other card that we can see?

 * There should be 1 "Corpse" cards.

It's a corpse! Your villager died,
and you only have a corpse now.
