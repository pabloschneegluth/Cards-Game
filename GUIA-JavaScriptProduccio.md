<!-- TOC -->
- [JavaScript Producció](#javascript-producció)
  - [Injecció de dependències](#injecció-de-dependències)
  - [Imports i Exports](#imports-i-exports)
  - [Els directoris](#els-directoris)
  - [Els components React](#els-components-react)
  - [El useMemo i el useCallback](#el-usememo-i-el-usecallback)
  - [Redux Slices](#redux-slices)
  - [Registrant Slices](#registrant-slices)
  - [Combine Reducers en un Slice](#combine-reducers-en-un-slice)
  - [Middleware](#middleware)
  - [Patró Middleware](#patró-middleware)
  - [Loading](#loading)
  - [useSelector i useDispatch](#useselector-i-usedispatch)
  - [El DOM](#el-dom)
<!-- /TOC -->

## JavaScript Producció

El codi Java de producció és a `src/www/`,
l'aplicació principal s'arrenca amb:

```java
import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { HashRouter } from "react-router-dom";

import "./index.css";

import { App } from "./App";
import { createAppStore } from "./createAppStore";

const root = ReactDOM.createRoot(document.getElementById("root"));
const store = createAppStore(global.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__);

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <HashRouter>
        <App />
      </HashRouter>
    </Provider>
  </React.StrictMode>,
);
```

Un cop heu instal·lat les dependències amb:

```bash
$ yarn
```

Podeu arrencar l'aplicació amb:

```bash
$ yarn start
```

Aquest codi és el que arrenca l'aplicació React, però
no el test. El test té una inicialització diferent.

La part <React.StrictMode> és per a que el codi React tingui
en compte les millors pràctiques, i doni missatges d'avís
si no es segueixen.

El store = createAppStore() és el que crea el store de 
Redux, que manté l'estat coherent a tota l'aplicació.

El <Provider> activa l'injecció de dependències de Redux
a tots els components de React. Això és el que permet
que els components React puguin accedir a l'estat de Redux
amb els hooks `useSelector` i `useDispatch`.

El <HashRouter> és el que permet que l'aplicació React
pugui fer navegació entre pàgines, sense que el servidor
hagi de fer res. S'usa el HashRouter perquè així no cal
configurar el servidor perquè faci servir el mateix
arxiu per a totes les pàgines.

El <App> és el component principal de React, que conté
totes les pàgines de l'aplicació. Aquest component és el
que s'encarrega de mostrar la pàgina corresponent a la
URL actual.

Aquest arxiu no s'ha de modificar, tots els canvis que
es facin s'han de fer arrelaran o bé a `src/www/App.js`
o bé a `src/www/createAppStore.js`.

El index.css és el fitxer CSS que s'aplica a tota 
l'aplicació. Aquest fitxer queda fora dels objectius de la
pràctica, i cap canvi que s'hi faci afectarà la nota.
Està a l'index.js per a que els estils grafics no afectin
als testos i aquests vagin més ràpid.

> Nota: global.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ és una
> variable global definida pel navegador quan s'instal·la
> l'extensió Redux DevTools. La URL per instal·lar-la és:
> https://chrome.google.com/webstore/detail/redux-devtools/lmhkpmbekcpmknklioeibfkpmmfibljd
> Quan s'instal·la, aquesta extensió afegeix una icona a la
> barra d'eines del navegador. Si s'obre aquesta icona, es
> pot veure l'estat de Redux i es poden fer proves
> interactives amb l'estat.

**Pregunta**: Si vui fer un canvi a l'store REDUX de 
l'aplicació, apartir de quin fitxer he de tocar?

*

### Injecció de dependències

JavaScript no té injector de dependències com ho té Java.
A Java Spring usa les anotacions i els tipus per fer les
injeccions automàticament, però això no està disponible.

Es podria usar un gestor de dependències com ho fa Java,
una mica més manual, però això dista del que es troba al
mercat, si s'ha decidit no afegir.

Això fà que les estratègies d'injecció de dependències 
de JavaScript i de Java siguin diferents. A JavaScript
no tenim res similar al patró Step que ens permeti
injectar múltiples implementacions d'una mateixa interfície.

Dos casos on aniria molt bé aquesta injecció automàtica
serien el  Router de pàgines, i el store de Redux. En el 
router es registarien sols els components que fan de pàgina,
i en el store es registriarien els reducers de cada part.
Tanmateix, aquestes injeccions no són possibles, i cal
canviar d'estratègia.

Així doncs, l'estratègia que s'ha fet és la següent: per 
cada _llista_ hi ha un fitxer que s'encarrega de registrar
els continguts, i cada cop que afegim un nou element, hem de 
modificar aquest fitxer. Això crea el problema en que hi ha
un sol punt que rep canvis cada cop que afegim un nou 
element, però això és el que es fa a JavaScript.

Les dues llistes són:

* `src/www/App.js` que conté la llista de pàgines a mostrar.
  Ho fa com un component React que conté tots els elements
  de pàgines a mostrar.

* `src/www/createAppStore.js` que conté la llista de 
  reducers i altres elements a afegir al store de Redux.
  Ho fa amb crides a combineReducers i applyMiddleware.

L'únic punt on hi ha injecció de dependències és en React.
React té un protocol anomenat Context que permet que els
components puguin injectar-se dades.

**Pregunta**: Si vui afegir una nova pàgina a l'aplicació,
on he de tocar?

* 

### Imports i Exports

Aquesta pràctica usa els imports i exports de JavaScript
per a fer recuperar els elements de cada fitxer. És
l'equivalent a fer `import` en Java amb una salvedat:
en Java exporta per defecte la classe amb el mateix nom
que el fitxer, i en JavaScript no. 

En JavaScript, per a exportar un element, s'usa la paraula
clau `export` davant de l'element a exportar. D'aquesta
manera, es pot exportar més d'un element per fitxer.

Per a importar un element, s'usa la paraula clau `import`,
el nom de l'element a importar i el nom del fitxer on està
l'element a importar. Es pot importar més d'un element
per fitxer.

Hi ha un patró d'imports i exports que es coneix com a
"barrel" que consisteix en exportar tots els elements
d'un directori en un sol fitxer. Això permet que els
altres fitxers no hagin de fer imports de tots els
fitxers d'un directori, sinó que només hagin de fer
un import del fitxer "barrel". 

A més, hi ha un fitxer clau anomenat `index.js` que és
el fitxer "barrel" de cada directori. Quan s'importa
un directori, s'importa el fitxer `index.js` d'aquest
directori. Això permet reemplaçar un fitxer per un
directori, i que tot segueixi funcionant. 

El JavaScript també afegeix automàticament l'extensió
`.js` als imports, per tant, si s'importa un fitxer
`src/www/App.js`, no cal posar l'extensió `.js`.

> Ara mateix tan sols els tests usen el patró barrel.
> Tant per les `actions` com per les `queries`.

**Pregunta**: Quins simbols exporta el ideaSlice.js?

*
*

### Els directoris

Els directoris s'han estructurat de la mateixa manera
que els paquets de Java. No coincideixen exactament en els
mateixos paquets, però segueixen la mateixa nomenclatura.

Per exemple, el directori `src/www/card` conté els fitxers
relatius a la lògica de negoci de carta, i conté els fitxers
per dibuixar cartes, descartar i redux:

* Card.js
* cardSlice.js
* Discard.js

**Pregunta**: Quins fitxers conté el directori 
`src/www/stack`?

*
*

### Els components React

Els components React són els que s'encarreguen de mostrar
els elements a l'usuari. Cada component React es troba en
un fitxer propi, i aquest exporta el component React.

Per convenció, els fitxers porten el mateix nom que el
component React que contenen, i comencen amb majúscula.
Aquests exporten un sol simbol, que és el component React.
Exemple a `src/www/idea`:

```javascript
import { Link } from "react-router-dom";
import { Loading } from "../loading/Loading";

export function Header() {
  return (
    <div className="header">
      <Link to="/blog">Blog</Link>
      <Link to="/game">Game</Link>
      <Loading />
    </div>
  );
}
```

Els components React segueixen la mateixa convenció que
les classes de Java. Així que els fitxers que semblin
contenir classes de Java, en realitat contenen components
React.

Es possible que algun fitxer contingui més d'un component
React. Per exemple, el fitxer `src/www/blog/BlogPAge.js` 
conté dos components React: `BlogPage` i `BlogListItem`.
Tanmateix, tan sols exporta el component `BlogPage`.
L'altre és l'equivalent a una classe interna de Java.
Si el fitxer creix, es pot extreure a un fitxer propi i
ser important. El VSCode ho fa automàticament amb la
funció "Refactor > Move to a new file".

**Pregunta**: Quins fitxers contenen components React al
directori `src/www/card`?

*
*

### El useMemo i el useCallback

Els components React s'executen cada cop que hi ha un
canvi en el seu estat o en els seus paràmetres. Això
significa que si un component React té un estat, cada
cop que aquest estat canvia, el component es torna a
executar.

Això pot ser molt lent, per tant, React permet que els
components React puguin memoritzar els seus resultats
internament, i no tornar a calcular-los si no cal.

Per a fer això, React proporciona dos hooks: `useMemo` i
`useCallback`. Els dos permeten memoritzar resultats
internament, però tenen diferències.

El `useMemo` permet memoritzar resultats de funcions.
El `useCallback` permet memoritzar funcions.

Per exemple, si tenim un component React que calcula
el resultat d'una funció, i aquest resultat es mostra
a l'usuari, podem memoritzar el resultat de la funció
amb `useMemo`, i això farà que el component no es torni
a calcular si no cal. 

```javascript
import { useMemo } from "react";

export function MyComponent() {
  const result = useMemo(() => {
    return calculateResult();
  }, []);

  return <div>{result}</div>;
}
```

En aquest cas, el component només es torna a calcular
si el paràmetre `[]` canvia. En aquest cas, el paràmetre
`[]` és un array buit, per tant, el component només es
torna a calcular si canvia el propi component.

Si el component React conté una funció que es passa a
un altre component React, podem memoritzar la funció
amb `useCallback`, i això farà que el component no es
torni a calcular si no cal.

```javascript
import { useCallback } from "react";

export function MyComponent() {
  const onClick = useCallback(() => {
    console.log("click");
  }, []);

  return <div onClick={onClick}>Click me</div>;
}
```

En aquest cas, el component només es torna a calcular
si el paràmetre `[]` canvia. En aquest cas, el paràmetre
`[]` és un array buit, per tant, el component només es
torna a calcular si canvia el propi component.

En aquest moment no hi ha cap component React que
utilitzi `useMemo` o `useCallback`, però és possible
que en el futur hi hagi.

**Pregunta**: Quin dels dos hooks permet memoritzar
resultats de funcions?

*

**Pregunta**: Quin dels dos hooks permet memoritzar
funcions?

*

### Redux Slices

El estat Redux s'ha dividit en slices. Cada slice conté
els seus propis estats, accions, reducers i middleware. Això 
permet que cada slice pugui ser independent, i que no
hagi cap dependència entre els slices.

Els slices són els fitxers que acaben amb `Slice.js`. Per
exemple, el fitxer `src/www/idea/ideaSlice.js` conté:

* Selectors: `selectAllIdeaNames` i `selectIdea`
* Accions: `replaceIdeas`, `requestDrawIdea`
* Reducer: `ideaReducer`
* Middleware: `ideaMiddleware`

Ja coneiem els selectors, les accions i el reducer. El 
middleware és una funció que s'executa cada cop que
s'executa una acció i permet executar codi addicional
quan s'executa una acció. És una mena d'interceptor.

Els middleware són molt útils per a fer peticions
HTTP, per exemple, per a obtenir dades del servidor, i
per encadenar accions, per exemple, per mantenir 
slices de Redux desacoblats, però sincronitzats.

**Pregunta**: Quins fitxers contenen slices de Redux?

*

**Pregunta**: Quins selectors conté el cardSlice?

*

### Registrant Slices

Els slices s'han de registrar a l'arrel de l'aplicació
en el fitxer `src/www/createAppStore.js`:

```javascript
import { applyMiddleware, createStore, combineReducers, compose } from "redux";
import reduxFreeze from "redux-freeze";

import { blogReducer, blogMiddleware } from "./blog/blogSlice";
import { cardReducer, cardMiddleware } from "./card/cardSlice";
import { ideaReducer, ideaMiddleware } from "./idea/ideaSlice";
import { stackReducer, stackMiddleware } from "./stack/stackSlice";
import { gameMiddleware } from "./game/gameSlice";
import { loadingReducer } from "./loading/loadingSlice";

const reducer = combineReducers({
  blog: blogReducer,
  card: cardReducer,
  idea: ideaReducer,
  stack: stackReducer,
  loading: loadingReducer,
});

const middleware = [
  blogMiddleware,
  cardMiddleware,
  gameMiddleware,
  ideaMiddleware,
  stackMiddleware,
  reduxFreeze,
];

export function createAppStore(composeEnhancers = compose) {
  return createStore(reducer, composeEnhancers(applyMiddleware(...middleware)));
}
```

El reduxFreeze és un middleware per evitar canvis 
accidentals a l'estat Redux. 

El `reducer` és un objecte que conté els reducers de
cada slice. 

El `middleware` és un array que conté els middleware
de cada slice.

Per tant, quan s'afegeixi un nou slice, s'ha de registrar
els seus reducers a l'objecte `reducer`, i els seus
middleware a l'array `middleware`.

**Pregunta**: Quins reducers s'han de registrar a l'objecte
`reducer`?

*

### Combine Reducers en un Slice

Els slices contenen un reducer, però aquest reducer
pot combinar altres reducers. Per exemple, el fitxer
`src/www/idea/ideaSlice.js` conté tres reducers:

```javascript
export const ideaReducer = combineReducers({
  byName: ideaByNameReducer,
  allNames: ideaNamesReducer,
});

function ideaByNameReducer(state = {}, action) {
  switch (action.type) {
    case REPLACE_IDEAS:
      return Object.fromEntries(action.ideas.map((c) => [c.name, c]));
    default:
      return state;
  }
}

function ideaNamesReducer(state = [], action) {
  switch (action.type) {
    case REPLACE_IDEAS:
      return action.ideas.map((c) => c.name);
    default:
      return state;
  }
}
```

El reducer `ideaReducer` té un nou reducer que combina els 
reducers `ideaByNameReducer` i `ideaNamesReducer`.

El `ideaByNameReducer` conté els estats de les idees per
nom. El `ideaNamesReducer` conté els noms de les idees.
Aquest patró correspon a una bona pràctica de Redux
https://redux.js.org/usage/structuring-reducers/normalizing-state-shape#designing-a-normalized-state
que busca optimitzar l'execució de Redux.

El objecte d'estat a redux resultant d'aquesta combinació
és:

```javascript
{
  idea: {
    byName: {
      "Idea 1": {
        name: "Idea 1",
        level: 1,
        xp: 0,
        tagRequirements: [],
        cardRewards: [],
      },
      "Idea 2": {
        name: "Idea 2",
        level: 1,
        xp: 0,
        tagRequirements: [],
        cardRewards: [],
      },
      },
    },
    allNames: ["Idea 1", "Idea 2"],
  },
}
```

Per tant, amb idea.allNames podem obtenir els noms de
totes les idees, i amb idea.byName["Idea 1"] podem
obtenir l'estat de l'idea amb nom "Idea 1".

**Pregunta**: Quins són els reducers que trobem dins de 
l'slice de card?

*
*

### Middleware

Els middleware són funcions que s'executen cada cop que
s'executa una acció. Tenen dos usos principals:

1. Executar accions REST per obtenir dades del servidor.
2. Encadenar accions.

Exemple:

```javascript
async function fetchDrawIdea(ideaId) {
  return backend.post(`/api/v1/game/ideas/${ideaId}/draw`, null);
}

export const ideaMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REPLACE_GAME) {
    store.dispatch(replaceIdeas(action.game.ideas));
  }
  if (action.type === REQUEST_DRAW_IDEA) {
    store.dispatch(showLoadingSpinner());
    const data = await fetchDrawIdea(action.ideaId);
    store.dispatch(replaceGame(data));
    store.dispatch(hideLoadingSpinner());
  }
};
```

En aquest exemple, quan es vol obtenir una carta d'una idea,
es fa dispatch d'una acció requestDrawIdea. Aquesta acció
ès interceptada pel middleware, que fa una crida REST per
obtenir la carta, i després fa un replaceGame per 
actualitzar l'estat del joc.

Al actualitzar l'estat del joc, es fa un replaceGame, que
és una acció de l'slice de game, però no de l'slice d'idea.
Per tant, el reducer d'idea no l'hauria d'executar. Però
com que el middleware d'idea també s'executa, aquest
middleware intercepta el replaceGame i fa un replaceIdeas
per actualitzar l'estat de les idees.

Mai hauriem d'utilitzar en un reducer una acció d'un altre
slice. Cal que usem el middleware per això.

Cal tenir en compte, que si el middleware fés també un 
replaceIdeas, després de demanar el replaceGame, es
podria correr el risc que en altres casos on es rep la
resposta del replaceGame, no es fes un replaceIdeas, i això
podria provocar que l'estat de les idees no estigui
actualitzat.

**Pregunta**: Quines accions intercepta el middleware de
card?

*
*

### Patró Middleware

Malgrat ser més potents, i molt versatils, en aquesta 
pràctica utilitzarem un patró de middleware molt concret:

```javascript
export const nomMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === ACCIO1) {
    // ...
  }
  //...
};
```

Cal que sempre hi hagi:

1. El export del middleware.
2. Una funció amb la forma (store) => (next) => async (action) => { ... }
   De fet, son tres funcions, però es pot tractar com una sola funció
   amb tres paràmetres.
3. Un next(action) al principi de la funció.
4. Un if amb el tipus d'acció que es vol interceptar.
5. Un async/await per a les crides REST.

El next(action) és important, ja que envia l'acció a
la resta de middleware, i al final al reducer.
Sense aquesta crida, l'acció no arribaria al reducer,
i no es produiria cap canvi a l'estat.

Cal que tots els middleware segueixin aquest patró.

**Pregunta**: Quin és el propòsit del paràmetre `next`?

*

### Loading

Hi ha una slice que conté l'estat de la pantalla de
càrrega. Aquesta slice conté un reducer i un middleware.

Cada cop que un middleware fa una crida REST, cal indicar
a l'slice de loading que s'està carregant dades, i quan
s'acaba la crida, cal indicar que ja s'ha acabat de
carregar.

Això és fà amb:

```javascript
 store.dispatch(showLoadingSpinner());  // << Indica que s'està carregant
const data = await fetchDrawIdea(action.ideaId);
store.dispatch(replaceGame(data));
store.dispatch(hideLoadingSpinner());   // << Indica que s'ha acabat de carregar
```

> Nota: Això s'utilitza principalment per simplificar la
programació del test. 

### useSelector i useDispatch

Els hooks useSelector i useDispatch són una forma estàndard
d'obtenir l'estat i fer dispatch d'accions.

```javascript
import { useDispatch, useSelector } from "react-redux";
import { selectIdea, requestDrawIdea } from "./ideaSlice";

export function Idea({ ideaName }) {
  const idea = useSelector((s) => selectIdea(s, ideaName));
  const dispatch = useDispatch();
  const drawIdea = () => dispatch(requestDrawIdea(idea.id));

  return /* ... */
}
```

Consultar els apunts de la pràctica de ES3 per a més.

### El DOM

El DOM és l'arbre de nodes que representa la pàgina web.
No accedirem mai directament al DOM, sinó que ho farem
amb React. Però React accedeix al DOM, i per tant, cal
saber quines propietats i events té cada node.

En general tots els nodes tenen les següents propietats:

* `className`: Una llista d'identificadors de classes.
* `style`: Un objecte amb les propietats CSS del node.

I els següents events:

* `onClick`: Quan es fa click sobre el node.
* `onSubmit`: Quan es fa submit d'un formulari.
* ...

Quan es programen aquests esdeveniments, React passa al
callback un objecte amb la informació de l'esdeveniment
anomenat `event`.

```javascript
const onClick = (event) => {
  console.log(event.target.value);
};
```

El target és el node que ha disparat l'esdeveniment.
A més disposa de mètodes com `preventDefault` o
`stopPropagation` per evitar que l'esdeveniment es
propagi a altres nodes.

Per a més informació, consultar la documentació de
Mozilla:

* https://developer.mozilla.org/en-US/docs/Web/API/Event

**Pregunta**: On es fa un `stopPropagation`?

*

**Pregunta**: Que passa si no es fa aquell `stopPropagation`?

*
