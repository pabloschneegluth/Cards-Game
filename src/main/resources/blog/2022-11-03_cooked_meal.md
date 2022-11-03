---
writer: 6Q
package: food
---
# Cooked meal

cooked food is much better and therefore more nutritious than raw food, to make it you just need to put it in the furnance.

## How to make cooked meal

| $CardName    |
|--------------|
| Berry        |
| Apple        |
| Cow          |
| Milk         |

| $Material    |
|--------------|
| Wood         |
| Coal         |

 * Given a new game.
 * Given 1 $CardName card, 1 $Material card and 1 "Furnance" card.
 * End the current moon.
 * There should be 0 $CardName card, 0 $Material card and 1 "Furnance" card.
 * There should be a 1 "cooked meal" card.


## Eating cooked meal
 * Given there are 1 "cooked meal" cards.
 * Given there are 5 "Villager" cards.
 * The sum of all "Eats" tags value should be 5.
 * The sum of all "Food" tags value should be 5.
 * End the current moon.
 * There should be 5 "Villager" cards.
 * There should be 0 "cooked meal" cards. 
 * The sum of all "Eats" tags value should be 5. 
 * The sum of all "Food" tags value should be 0.
