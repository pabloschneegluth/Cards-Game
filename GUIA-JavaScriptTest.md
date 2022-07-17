<!-- TOC -->
- [JavaScript Test](#javascript-test)
  - [El fitxer de test](#el-fitxer-de-test)
  - [El context](#el-context)
  - [El Context: beforeTest](#el-context-beforetest)
  - [El Context: given](#el-context-given)
  - [El Context: acció](#el-context-acció)
  - [El Context: should](#el-context-should)
  - [Accions](#accions)
  - [Afegir acció](#afegir-acció)
  - [Queries](#queries)
  - [Queries: Verbs](#queries-verbs)
  - [Queries: Tipus](#queries-tipus)
  - [Queries: ByCriteria](#queries-bycriteria)
  - [Queries: Primer argument](#queries-primer-argument)
  - [Afegir query](#afegir-query)
  - [Screen Debug](#screen-debug)
  - [Objectes Digest](#objectes-digest)
<!-- /TOC -->

## JavaScript Test

El codi Java de test és a `src/www/__test__`. Els
arxius de test es crean automàticament a partir dels arxius 
post amb `yarn create-tests`. Els arxius de test es creen
als corresponents directoris de test.

Un exemple de test és:

* Post_20220717_BushesVillagersAndBerries_Test
* Post_20220717_BushesVillagersAndBerries_Context

El primer és el test, el segón és l'implementació.

Per executar els tests, executar `yarn test`.

**Pregunta**: Com és diu la classe de context del 
test Post_20220723_Ideas_Test?

*

### El fitxer de test

Un exemple de fitxer de test és:

```javascript
test("2022-07-17_bushes_villagers_and_berries.md", async () => {
  await runBeforeTestStarts(
    "2022-07-17_bushes_villagers_and_berries",
    "187c6ace9199129be617f29fe9a5cfe4"
  );

  const context = new Post_20220717_BushesVillagersAndBerries_Context();
  await context.beforeTest();

  // # Bushes, Villagers and Berries                      // # Bushes, Villagers and Berries

  // ## The game                                          // ## The game
  await context.enterTheGame(); //                        // * Enter the game.
  await context.thereShouldBeNSCards(1, "Villager"); //   // * There should be 1 "Villager" cards.
  await context.thereShouldBeNSCards(1, "Berry Bush"); // // * There should be 1 "Berry Bush" cards.
  await context.thereShouldBeNSCards(1, "Berry"); //      // * There should be 1 "Berry" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
```

La funció `test` és una funció de Jest equivalent a la
anotació `@Test` de JUnit. Descriu el nom del test i la
funció que l'executa.

La funció `runBeforeTestStarts` és una funció de test
que s'executa abans de cada test i prepara l'entorn,
entre ells, les comunicacións amb el servidor. Rep
com a paràmetres el nom del post i el hash del contingut
del post. Això és per assegurar que el contingut del post
és el que s'espera. Si no ho fos, el test fallaria i 
demanaria tornar a executar `yarn create-tests` i els
testos de backend.

> Important: Els testos de backend s'executen sempre 
> abans que els testos de frontend.

La funció `runWhenTestSuccessful` és una funció de test
que s'executa al final del test quan el test ha estat 
correcte. Aquesta acaba de fer les comprovacions 
neceessàries per assegurar que el test ha estat correcte.
Entre altres coses, comprova que s'hagin fet totes les
cridades al servidor que s'esperaven.

Les funcions `context.beforeTest` i `context.afterTest`
són funcions de test que s'executen abans i després de
cada test. 

La resta de crides a context segueixen els mateixos
principis que els vistos en els testos de backend.

**Pregunta**: Que cal executar abans, els tests de backend
o els de frontend?

*

### El context

Igual que en els testos de backend, el context és una
classe que conté les funcions que s'executen en el test.

Un exemple de context és:

```javascript
export class Post_20220717_BushesVillagersAndBerries_Context {
  async beforeTest() {}

  async enterTheGame() {
    // text:  * Enter in the game.
    // code:  await this.enterTheGame();
    await waitForEnterTheGame();
  }

  async thereShouldBeNSCards(expectedCount, cardName) {
    // text:  * There should be 1 "Villager" card.
    // code:  await this.thereShouldBeNSCards(1, "Villager");

    const cards = getAllCardByName(mainView, cardName);
    expect(cards).toHaveLength(expectedCount);
  }

  async afterTest() {}
}
```

Normalment el mètode `afterTest` no cal que es faci res.

Sobre els altres, caldrà distingir entre els mètodes
beforeTest, given, acció, i should.

**Pregunta**: Què exporta el fitxer corresponent al context 
del test
`Post_20220721_MoreDetailsAboutHowVillagersEatFood_Test.spec.js`?

*

### El Context: beforeTest

El mètode `beforeTest` és un mètode que s'executa abans
de cada test. Normalment entrarà a la partida:

```javascript
async beforeTest() {
  await waitForEnterTheGame();
}
```

És fa així perquè normalment el primer que fa el jugador
és entrar en la partida, i assumim que el test ja comença
després d'haver entrat en la partida.

> Nota: Hi ha un await davant de la crida a 
> `waitForEnterTheGame`. Això és perquè el mètode 
> `waitForEnterTheGame` és una funció que retorna una
> promesa. Per tant, cal esperar a que la promesa es
> resolgui abans de continuar.

**Pregunta**: Què fa el mètode `waitForEnterTheGame`?

*

### El Context: given

El mètodes `given` són els que preparen l'estat inicial
del backend. Normalment són mètodes que s'executen
tan sols en el backend, i dels quals el frontend no
fa res.

Tanmateix, si que cal sincronitzar l'estat del frontend
amb l'estat del backend. Per això al test de backend es 
demana que sempre es simuli una crida del frontend al 
servidor, per assegurar que el frontend s'ha sincronitzat
correctament amb el backend.

És per això, que malgrat no es faci res en el frontend,
sempre es simuli que l'usuari fa un reload:

```javascript
async givenANewGameWithAStackOfNSCardsAndNSCards() {
  // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  // code: await this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")

  await waitForReloadGame();
}
```

> Nota: Hi ha un await davant de la crida a 
> `waitForReloadGame`. Això és perquè el mètode
> `waitForReloadGame` és una funció que retorna una
> promesa. Per tant, cal esperar a que la promesa es
> resolgui abans de continuar.

**Pregunta**: Què fa el mètode `waitForReloadGame`?

*

### El Context: acció

El mètodes `acció` són els que simulen l'usuari fent 
coses. Normalment sempre executaran functions de
`waitFor*` amb un await al davant.

Per exemple:

```javascript
async endTheCurrentMoon() {
  // text:  * End the current moon.
  // code: await this.endTheCurrentMoon()
  // hint: Post_20220725_IdeasHaveLevels_Context.endTheCurrentMoon

  await waitForEndMoon();
}
```

Tanmateix, a més d'executar l'acció, és possible que algunes
vegades calgui obtenir dades de la pantalla per a poder
posar els arguments de la següent acció. 

**Pregunta**: Quins mètodes de context usen la funció
`waitForMoveCardOnTopOf`?

*

### El Context: should

El mètodes `should` són els que comproven que l'estat
del frontend és el que s'espera. En aquest cas, vol dir
que miren que ha pintat React a la pantalla i agafen
dades del DOM per a comprovar que són les esperades.

S'usa principalment la llibreria `@testing-library/react`
per a obtenir els elements de la pantalla a través del
que s'anomena `queries`.

Un cop s'ha obtingut l'element, es pot comprovar que
és el que s'espera.

A més de les comprovacions que inclou `jest`, també
s'afegeixen funcions de `@testing-library/jest-dom` per
a comprovar més coses.

Podeu trobar més informació a:
- https://github.com/testing-library/jest-dom

Exemple:

```javascript
async theSShouldHaveLevelNAndNXp(ideaName, level, xp) {
  // text:  * The "Harvest Idea" should have level 1 and 0 XP.
  // code: await this.theSShouldHaveLevelNAndNXp("Harvest Idea", 1, 0)

  var idea = getIdeaDigestByName(mainView, ideaName);
  expect(idea).toMatchObject({ level, xp });
}
```

> Nota: Les queries que porten al nom Digest, són queries
> que en comptes de retornar un element, retornen un
> objecte amb les dades que s'han trobat a la pantalla.
> D'aquesta manera, és més fàcil comprovar que les dades
> són les que s'esperen, i debugar.

**Pregunta**: Quins mètodes de context usen la funció
`queryAllStackDigestByCardNames`?

*
*

### Accions

Els directoris de test tenen subdirectoris que s'anomenen
`actions`. Aquests directoris contenen els mètodes que 
simulen les accions de l'usuari sobre l'aplicació.

Accions simples es troben main i son:

* `src/www/__test__/main/actions/click`
* `src/www/__test__/main/actions/move`

Però també en trobem de més complexes, començant per:

* `src/www/__test__/main/loading/waitForLoading`

L'acció `waitForLoading` és una acció que espera a que
la pantalla de carregant hagi desaparegut. És molt important
per poder sincronitzar la recepció de dades del frontend 
amb el backend dins del test. L'acció `waitForLoading`
requereix sempre un await:

```javascript
await waitForLoading();
```

I per tant totes les que s'en deriven també, com per 
exemple (a game):

```javascript
await waitForEnterTheGame();
await waitForReloadGame();
```

Totes es les accions d'un paquest es poden obtenir 
direcament amb import del barrel:

```javascript
import { waitForLoading } from "../main/actions";
```

Altres directoris tenen altres accions.

**Pregunta**: Quines accions té el directori 
d'stack/actions?

*

### Afegir acció

Per afegir una nova acció:

1. Si el paquet (directori) no existeix, crear-lo.
2. Si el paquet no conte un directori `actions`, crear-lo.
3. Crear el fitxer amb el nom de funció de la acció.
4. Implementar i exportar la funció en el fitxer.
5. Crear el fitxer `index.js` si no existeix.
6. Exportar la funció del fitxer en el fitxer `index.js`.

Un exemple de barrel és:

```javascript
// www/__test__/stack/actions/index.js
export * from "./waitForMoveCardOnTopOf";
export * from "./waitForMoveCardToItsOwnStack";
```

**Pregunta**: Si volem crear una nova acció que es digui
`waitForMoveMilitaOnTopOfCrits` del paquet militia, a quin
fitxer index s'ha d'afegir?

* 

### Queries

Les queries també s'organitzen en barrels i es troben
dins del subdirectori `queries` de cada directori de test.

Aquestes corresponen a les queries que es fan a la pantalla
per obtenir el seu contingut. En quant a nom i arguments 
segueixen l'estandar de `@testing-library/react`. Veure:
- https://testing-library.com/docs/queries/about

> Malgrat que recomana usar primer les ByRole, ByLabelText,
> ByPlaceholderText, ByDisplayValue, ByAltText i ByTitle,
> s'han utilitzat les de ByTestId i una adicional 
> ByAttribute per motius de velocitat d'execució de test.

Les queries es descomposen en tres parts:

```
[VERB][TIPUS][BYCRITERIA]
```

Per exemple, en `queryAllCardDigestByName`:

* Verb: `queryAll`
* Tipus: `CardDigest`
* ByCriteria: `ByName`

**Pregunta**: Indica quin és el verb, tipus i criteri de
`queryAllStackDigestByCardNames`:

* Verb:
* Tipus:
* ByCriteria:

### Queries: Verbs

En aquest exemple:

* Els verbs son: query, queryAll, get i getAll
* El tipus és: CardDigest
* El criteri és: ByName

I totes estan definides al fitxer 
`queryAllCardDigestByName`.

> Nota: No fem servir les queries findBy* de la
> @testing-library perquè les 
> substituim per les accions waitFor* que són més 
> senzilles d'usar de forma fiable.

Cada verb té un significat especial:

* query retorna:
  - un element si existeix, 
  - null si no existeix
  - o fa un throw si hi ha més d'un element
* queryAll retorna:
  - un array d'elements en tots els casos
* get retorna:
  - un element si existeix,
  - o fa un throw si no existeix
  - o fa un throw si hi ha més d'un element
* getAll retorna:
  - un array d'elements si hi ha un o més elements
  - o fa un throw si no hi ha cap element

Aquestes queries estan dissenyades per aportar informació
sobre l'èxit del que s'espera i donar errors més fàcils
de llegir. Hi ha una taula resum:

| Name          | No match | 1 match | 1+ match |
| ------------- | -------- | ------- | -------- |
| getBy...      | throw    | return  | throw    |
| queryBy...    | null     | return  | throw    |
| getAllBy...   | throw    | array   | array    |
| queryAllBy... | []       | array   | array    |

El fitxer porta el nom del `queryAll` malgrat exportar
els quatre verbs perquè la resta són derivades d'ell.

**Pregunta**: Si vulguéssim usar una query que agafés
un IdeaDigest ByCardReward, però que fallés si hi ha 
cap o més d'un, quin seria el nom de la query?

*

### Queries: Tipus

Els tipus són el tipus d'element que retornen les queries.

N'hi ha de dos diferents:

1. Elements DOM
2. Objectes Digest

Els elements DOM es coneixen com simplement element, i 
corresponen als elements HTML que es troben a la pantalla.
Per saber quins atributs tenen i com accedir, consultar
la documentació de Mozilla:

- https://developer.mozilla.org/en-US/docs/Web/HTML/Element

Si es volen debugar, podeu usar la funció `debug` de 
`@testing-library/react`:

```javascript
import { screen } from "@testing-library/react";

function ...() {
  // per veure un element concret
  screen.debug(element);
  // per veure tota la pantalla
  screen.debug();
}
```

Normalment, els elements DOM es poden utilitzar per 
encadenar altres queries.

Els Objectes Digest són objectes que s'han creat de les
dades que hi ha a la pantalla. Aquests objectes contenen
informació sobre l'estat de la pantalla i són més fàcils
de utilitzar que els elements DOM.

Per veure el contingut d'un objecte Digest, podeu usar
el mètode `debug` del propi objecte, o bé `debugElement`
per veure l'element DOM que l'ha creat.

```javascript
const cardDigest = getCardDigestByName("Milita");
cardDigest.debug();
cardDigest.debugElement();
const cardElement = cardDigest.getElement();
```

**Pregunta**: Quin és el tipus de `getCardDigestByName`?

* 

### Queries: ByCriteria

Els criteris són els criteris que utilitza la query per
seleccionar els elements que es volen.

Pot ser que no estigui present, com en el cas de
`queryAllCardDigest` que retorna tots sense filtre,
o fins i tot, es podrien especifica més d'un com es
fan en les queries dels Repository de Java JPA.

En cas d'existir un criteri, aquest es defineix com
el segon argument de la funció de query.

**Pregunta**: Quin és el criteri de `queryCardDigestByName`?

*

### Queries: Primer argument

El primer argument de tota la query és on buscar l'element.
Aquest argument és obligatori.

Exemple:

```javascript
import { mainView } from "../main";

/* Per buscar a tota la pantalla */
var actual = getIdeaDigestByName(mainView, expectedName);

/* Per buscar al side view */
var sideView = getSideView(mainView);
var actual = getIdeaDigestByName(sideView, expectedName);
```

**Pregunta**: Quin és el primer argument de `getCardDigestByName`
si volem buscar a tota la pantalla?

*

### Afegir query

Per afegir una nova query:

1. Si el paquet (directori) no existeix, crear-lo.
2. Si el paquet no conte un directori `queries`, crear-lo.
3. Crear el fitxer amb el nom de funció de la query.
4. Implementar i exportar la funció en el fitxer.
5. Crear el fitxer `index.js` si no existeix.
6. Exportar la funció del fitxer en el fitxer `index.js`.

Un exemple de barrel és:

```javascript
// www/__test__/stack/queries/index.js
export * from "./digestStackElement";
export * from "./queryAllStack";
export * from "./queryAllStackByPosition";
export * from "./queryAllStackDigest";
export * from "./queryAllStackDigestByCardNames";
```

> Nota: Recorda que el nom de fitxer sempre cal que
> correspongui a la funció del verb `queryAll`.

**Pregunta**: Si volem crear una nova query que es digui
`getAllMilitiaByLevel` del paquet militia, a quin
fitxer index s'ha d'afegir?

* 

### Screen Debug

Usa `screen.debug()` per imprimir l'arbre DOM i veure què es 
renderitzaria a la pantalla per l'usuari.

Pots també usar `screen.debug(element)` per imprimir l'arbre 
DOM d'un element, enlloc de la pantalla sencera.

Importa `screen` de `@testing-library/react`:

```js
import { screen } from '@testing-library/react';

function example(cardElement) {
  screen.debug(cardElement);
}
```

Si estàs utilitzant digests, tenen un mètode anomenat 
`debugElement` que fa el mateix.


```js
function example(cardDigest) {
  cardDigest.debugElement();
}
```

El resultat hauria de ser:

```html
console.log
      <div
        data-cardid="villager-1"
        data-cardname="Villager"
        data-testid="card"
        data-zindex="0"
      >
        Villager
        <div
          data-tagname="Eats"
        >
          1
        </div>
        <div
          data-tagname="Worker"
        >
          1
        </div>
        <div
          data-carddescriptionterm="Fruit"
        >
          Fruit
            is 
          None
        </div>
      </div>
  
  /Volumes/Projects/tecnocampus/LS2/2022/classroom--cards-game--2022/src/www/__test__/idea/Post_20220723_Ideas_Context.js:89:10
    87 |
    88 |     const [card] = getAllCardDigestByName(mainView, cardName);
  > 89 |     card.debugElement();
       |          ^
```

**Pregunta**: Si vui veure el contingut de la pantalla
sencera, quina funció de `screen` s'ha d'usar?

*

### Objectes Digest

Els objectes Digest són els objectes que s'han creat de les
dades que hi ha a la pantalla. Aquests objectes contenen
informació sobre l'estat de la pantalla i són més fàcils
de utilitzar que els elements DOM.

Exemples són els objectes digest de les cartes, dels
stacks i de les idees.

Crea les funcions de digest amb `createDigestFunction`. 
Això afegirà propietats addicionals que permetran tenir
millors utilitats de depuració.

La nomeclatura de les funcions de digest és 
digest[TipusElement]Element.

Un exemple de creació de digest és `digestStackElement`:

```js
export const digestStackElement = createDigestFunction(
  "stack",
  (stackElement) => {
    const stackDigest = {
      position: +stackElement.dataset.stackposition,
      cards: queryAllCardDigest(stackElement).sort(
        (a, b) => a.zindex - b.zindex,
      ),
    };

    return stackDigest;
  },
);
```

En aqueste cas es crea un digest amb dos camps, `position` i `cards`,
i camps addicionals de suport:

* `typeName: El nom del digest, en aquest cas `stack`.
* `getElement`: Una funció que retorna l'element DOM del digest.
* `debugElement`: Una funció que imprimeix l'element DOM del digest.
* `debug`: Una funció que imprimeix el digest.

Pots afegir les teves funcions al digest, i aquestes estaràn 
disponibles per a tots els usuaris. Mira `digestCardElement`
per un exemple.

Pots depurar el contingut del digest amb `debug`:

```js
  cardDigest.debug();
```

El resultat hauria de ser:

```js
console.log
        const cardDigest = {
          typeName: "card",
          id: "berry-bush-1",
          name: "Berry Bush",
          zindex: 2,
          terms: { Fruit: "Berry" },
          tags: { "Fruit Plant": 1 },
          getElement() { return /* the DOM element of this digest */ },
          debugElement() { /* shows the DOM html of this element */ },
          debug() { /* shows this message */ },
          getTag(...) { ... },
        }; 

    /Volumes/Projects/tecnocampus/LS2/2022/classroom--cards-game--2022/src/www/__test__/idea/Post_20220723_Ideas_Context.js:89:10
    87 |
    88 |     const [card] = getAllCardDigestByName(mainView, cardName);
  > 89 |     card.debug();
      |          ^
```

**Pregunta**: Com es diu la funció que crea el objecte
digest de les idees donat un element?

*

## Await en els tests frontend

Tots els mètodes de test són `async` perquè poden voler
esperar per carregar dades.

Per favor, no utilitzis cap funció per carregar dades, sinó
`await waitForLoading();`.

L'idea és que el middleware mostra el spinner abans de fer
la crida a l'API del Backend, llavors resol la crida, i una
vegada ha acabat, amaga el spinner. El middleware hauria de
tenir aquest aspecte:

```js
export const myMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === MY_ACTION_TYPE) {
    store.dispatch(showLoadingSpinner());   // <<<<<<< Add This
    const data = await fetchMyData();
    store.dispatch(replaceMyData(data));
    store.dispatch(hideLoadingSpinner());  // <<<<<<< Add This
  }
};
```

Per favor, tingues en compte les dues crides al store, primer
`showLoadingSpinner`, i al final, `hideLoadingSpinner`.

La funció `waitForLoading` s'apropa a això, primer per assegurar-se
que una crida a l'API del Backend ha començat, i llavors, per
esperar a que acabi.

Nota que qualsevol altre manera de carregar dades és molt
perillosa i pot amagar molts errors, a més de molts missatges
d'error com:

```
When testing, code that causes React state updates should be wrapped into act(...):
     
     act(() => {
       /* fire events that update state */
     });
     /* assert on the output */
    
This ensures that you're testing the behavior the user would see in the browser. Learn more at https://fb.me/react-wrap-tests-with-act
```
