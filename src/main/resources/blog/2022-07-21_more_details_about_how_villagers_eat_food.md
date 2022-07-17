---
writer: drpicox
coder: drpicox
package: tag.food
---
# More Details About How Villagers Eat Food

We have seen that we have villagers, and we have berries.
It turns out that villagers need food to survive. If not, they die.

Let's see how eating works.

## More kinds of villagers

Villagers eat one food, but there are other cards that eats
more than one. Like Militia, they eat two of food. 
Let's see it:

 * Given there are 1 "Villager" and 1 "Militia" cards.
 * The "Villager" card should have 1 in "Eats" tag.
 * The "Militia" card should have 2 in "Eats" tag.
 * The sum of all "Eats" tags value should be 3.

## More kinds of food

And there are also different kinds of food cards:

 * Given there are 1 "Berry" and 1 "Apple" cards.
 * The "Berry" card should have 1 in "Food" tag.
 * The "Apple" card should have 2 in "Food" tag.
 * The sum of all "Food" tags value should be 3.

## Eating

Eating works as expected, in this case, all villagers eats all available food:

 * End the current moon.
 * The sum of all "Eats" tags value should be 3.
 * The sum of all "Food" tags value should be 0.

## Too much food

If there is more food than villagers, the additional food is kept for the next moon.

 * Given there are 4 "Berry" and 0 "Apple" cards.
 * The sum of all "Eats" tags value should be 3.
 * The sum of all "Food" tags value should be 4.
 * End the current moon.
 * The sum of all "Eats" tags value should be 3.
 * The sum of all "Food" tags value should be 1.
 * There should be 1 "Berry" cards.

But what happens with half cards? could we eat half Apple?

 * Given there are 0 "Berry" and 2 "Apple" cards.
 * The sum of all "Eats" tags value should be 3.
 * The sum of all "Food" tags value should be 4.
 * End the current moon.
 * The sum of all "Eats" tags value should be 3.
 * The sum of all "Food" tags value should be 0.
 * There should be 0 "Apple" cards.

There is no Apple card left.

## Too few food

What happens when there are more eaters than food? 
We have already seen that they starve to dead.

But what with half foods? It turns out that they
are polite, and they leave the food untouched.

 * Given there are 0 "Villager" and 2 "Militia" cards.
 * Given there are 3 "Berry" and 0 "Apple" cards.
 * The sum of all "Eats" tags value should be 4.
 * The sum of all "Food" tags value should be 3.
 * End the current moon.
 * The sum of all "Eats" tags value should be 2.
 * The sum of all "Food" tags value should be 1.
 * There should be 1 "Militia" cards.
 * There should be 1 "Berry" cards.

So, at least they starve letting the future generations an option.

## Choosing what to eat

Villagers eat the first food that most adjust to their needs.

| $Villagers | $Militia | $Berries | $Apples | $ResultBerries | $ResultApples |
|------------|----------|----------|---------|----------------|---------------|
| 1          | 0        | 1        | 0       | 0              | 0             |
| 1          | 0        | 0        | 1       | 0              | 0             |
| 1          | 0        | 1        | 1       | 0              | 1             |
| 0          | 1        | 2        | 0       | 0              | 0             |
| 0          | 1        | 0        | 1       | 0              | 0             |
| 0          | 1        | 2        | 1       | 2              | 0             |
| 1          | 1        | 2        | 2       | 1              | 1             |

 * Given there are $Villagers "Villager" and $Militia "Militia" cards.
 * Given there are $Berries "Berry" and $Apples "Apple" cards.
 * End the current moon.
 * There should be $ResultBerries "Berry" cards.
 * There should be $ResultApples "Apple" cards.


