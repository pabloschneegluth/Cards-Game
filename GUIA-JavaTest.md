<!-- TOC -->
- [Java Test](#java-test)
  - [La classe de Test](#la-classe-de-test)
  - [El mètode Test Post](#el-mètode-test-post)
  - [Els mètodes del post](#els-mètodes-del-post)
  - [Regles bàsiques sobre implementació de Context](#regles-bàsiques-sobre-implementació-de-context)
  - [Regles d'implementació mètodes Context.given](#regles-dimplementació-mètodes-contextgiven)
  - [Regles d'implementació mètodes Context.acció](#regles-dimplementació-mètodes-contextacció)
  - [Regles d'implementació mètodes Context.should](#regles-dimplementació-mètodes-contextshould)
  - [Given @Services](#given-services)
  - [TipusListDTO](#tipuslistdto)
  - [HasName, HasNames, HasPosition](#hasname-hasnames-hasposition)
<!-- /TOC -->

## Java Test

Les classes de test de Java es construeixen 
automàticament amb `yarn create-tests`. La localització
correspon al package que hi ha al frontmatter de cada
post. El nom del test correspon al nom del post.

Com el test es genera automàticament, no es pot modificar,
i no es pot implementar el seu codi. Però, el test delega
en una classe Context del mateix nom per implementar
el codi de test corresponent a cada una de les frases
del post.

Un exemple del package game és:

* Post_20220717_BushesVillagersAndBerries_Test
* Post_20220717_BushesVillagersAndBerries_Context

El primer és el test, el segón és l'implementació.

El codi Java de test és a `src/test/java/`,
i s'arrenquen amb l'IDE o "./mvnw test".

**Pregunta**: Com és diu la classe de context del 
test Post_20220723_Ideas_Test?

*

### La classe de Test

La classe de test 

```java
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class Post_20220717_BushesVillagersAndBerries_Test {

    @Autowired Post_20220717_BushesVillagersAndBerries_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-07-17_bushes_villagers_and_berries", "187c6ace9199129be617f29fe9a5cfe4");
        context.beforeTest();

        // # Bushes, Villagers and Berries             // # Bushes, Villagers and Berries

        // ## The game                                 // ## The game
        context.enterTheGame();                        // * Enter the game.
        context.thereShouldBeNSCards(1, "Villager");   // * There should be 1 "Villager" cards.
        context.thereShouldBeNSCards(1, "Berry Bush"); // * There should be 1 "Berry Bush" cards.
        context.thereShouldBeNSCards(1, "Berry");      // * There should be 1 "Berry" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }
}
```

De les anotacions de la classe:

* L'@ActiveProfiles("test") és necessari per indicar a Spring
que estem en un entorn de Test, i així, poder disposar de
mocks.

* El @SpringBootTest és necessari per indicar al JUnit, que
aquesta classe és de test d'Spring i per tant, que cal
arrencar-lo.

* El @AutoConfigureMockMvc és necessari per indicar
a Spring que simularem comunicacions REST i, per tant,
que arrenqui i carregui els @RestController.

En la implementació de la classe, les anotacions:

* @Autowired és com Spring ens permet injectar 
dependències (i els seus singletons de s minúscula)
en les classes JUnit. No usar mai fora de les classes
de test.

* @Test indica al JUnit que el mètode és un test a 
executar. El test passa si el mètode no fa throw, el 
test falla si fa throw. Un assert quan falla fa throw
d'una excepció.

**Pregunta**: Quina anotació indica al JUnit que 
aquest test necessita arrencar Spring?

### El mètode Test Post

En la classe de test, el mètode testPost verifica que tot 
funcioni, i es genera automàticament amb
`yarn create-tests`.

El testUntils.runBeforeTestStarts prepara l'entorn 
d'execució. Usa els dos arguments per verificar que el
post no ha canviat, i que el test és vàlid. El primer 
argument és el nom del post, i el segon és el resum
HASH. Torna a llegir el post, i verifica que el HASH
coincideix. Si coincideix prossegueix, si no, falla i demana
que es torni a executar `yarn create-tests`.

El context.beforeTest demana a l'entorn que prepari
el context específic d'aquest test. Això pot ser crear 
la partida, posar certes cartes, etc.

El context.afterTest dona l'oportunitat de netejar
dades perquè no es quedin a les següents execucions.
Normalment no fará res.

El testUtils.runWhenTestSucessful para l'entorn de
d'execució. Entre les seves tasques està l'esborrat 
de la base de dades, així queda neta per la següent
execució; i també guarda totes les crides a API REST
que s'han fet perquè el frontend pugui usar-les.

Totes aquestes crides són fixes i estan presents en tots
els tests.

**Pregunta**: Quina crida esborra la base de dades
per a que els següents tests puguin arrencar de zero?

* 

### Els mètodes del post

El post té parts que comencen per ` * ` (espai,
asterisc, espai) que indiquen instruccions a executar
en el test. Exemple:

```md
 * Enter the game.
 * There should be 1 "Villager" cards.
 * There should be 1 "Berry Bush" cards.
 * There should be 1 "Berry" cards.
```

Aquestes instruccions són traduïdes per crides a 
mètodes de context. Ex:

```java
context.enterTheGame();
context.thereShouldBeNSCards(1, "Villager");
context.thereShouldBeNSCards(1, "Berry Bush");
context.thereShouldBeNSCards(1, "Berry");
```

Les paraules de la frase passen a formar part del nom
del mètode, seguint la convenció de Java. Els números
i els strings delimititats per "comes", es passen com
arguments i se substitueixen per N i S corresponentment.
S'ignoren la resta de caràcters.

En aquest exemple es pot veure que _Enter the game._
es tradueix a enterTheGame. I que la resta, es tradueixen
a crides a thereShouldBeNSCards, on la N és el primer
argument, i la S és el segon. Aquí podem veure com
el mateix mètode es crida diverses vegades.

**Pregunta**: Del post `2022-07-23_ideas`, el pas
_The "Harvest Idea" idea should require 1 card with at least 1 in "Fruit Plant" tag_, 
a quin mètode del context crida?

* 

*** El Context File

El context és on programem el codi de test. La instància
la construeix Spring, per tant, accepta injecció de 
dependències com la resta del codi Java, i és invocat
pel test corresponent. 

Cada test té la seva classe de context particular. Això
vol dir que encara que dos testos puguin usar les
mateixes frases, la implementació no serà la mateixa.
O es repetirà, o bé s'encapsularà en un artefacte d'ús
comú.

Una classe de context d'exemple és 
Post_20220717_BushesVillagersAndBerries_Context:

```java
import static com.drpicox.game.card.api.CardListDTO.findAllCard;
import static com.drpicox.game.util.Names.byName;

@Component
public class Post_20220717_BushesVillagersAndBerries_Context {

    private final FrontendSimulator frontendSimulator;

    private GameDTO gameDTO;

    public Post_20220717_BushesVillagersAndBerries_Context(FrontendSimulator frontendSimulator) {
        this.frontendSimulator = frontendSimulator;
    }

    public void beforeTest() {}

    public void enterTheGame() {
        // text:  * Enter in the game.
        // codi: this.enterTheGame();
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void thereShouldBeNSCards(int expectedCount, String cardName) {
        // text:  * There should be 1 "Villager" cards.
        // codi: this.thereShouldBeNSCards(1, "Villager")
        var matchingCards = findAllCard(gameDTO, byName(cardName));
        assertThat(matchingCards).hasSize(expectedCount);
    }

    public void afterTest() {}
}
```

Els import statics cal afegir-los quan siguin necessaris. 
En aquest cas el findAllCard és una utilitat que extrau les
cartes d'un gameDTO, i el byName és un Predicat sobre
carta que permet filtrar aquells que tinguin el nom
corresponent.

El @Component és necessari. Cal indicar a Spring que 
aquesta classe és un Singleton s minúscula i cal injectar-la.

El FrontendSimulator és el mecanisme que usarem per
simular crides del frontend al backend. L'injecta al 
constructor Spring, i podem veure el seu ús a 
enterTheGame. En aquest cas demana la partida actual
com ho faria el frontend, i es guarda el gameDTO 
de resposta.

El gameDTO és el resultat de l'última crida al backend a 
través de l'API de Rest. Cal guardar-lo sempre, i totes les
comprovacions cal fer-les sobre el gameDTO. El 
gameDTO és l'estat que tindrà el frontend, i, per tant, és
el lloc on comprovar que tot funciona correctament.

El beforeTest en aquest cas està buit. El motiu és que
aquest test comprova entrar en la partida. En general,
la resta assumirà que ja hi ha partida, i aquesta s'iniciarà
al beforeTest. El `yarn create-tests`crearà un esquelet
de contingut prou raonable per la majoria de casos.

L'afterTest normalment estarà buit.

### Regles bàsiques sobre implementació de Context

En el beforeTest iniciarem la partida, i posarem totes 
les cartes per assegurar que no falli res. Normalment, 
cartes de menjar perquè els vilatans no morin de gana.

Dels mètodes del post, n'hi distingim de tres tipus:
* Givens
* Accions
* Shoulds

### Regles d'implementació mètodes Context.given

Els givens inicialitzen la partida i expliquen al jugador 
el que necessiten saber per entendre les accions que
succeiran després. Els givens es descomponen en dues
parts: 

  1. Modificar estat del joc.

  2. Recuperar l'estat del joc.

```java
public class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context { ...
    public void givenThereAreNSAndNSCards(int count1, String name1, int count2, String name2) {
        // text:  * Given there are 2 "Villager" and 2 "Trader" cards.
        // code: this.givenThereAreNSAndNSCards(2, "Villager", 2, "Trader")

        // 1: Modificar l'estat del joc
        givenCardService.givenCards(count1, name1);
        givenCardService.givenCards(count2, name2);

        // 2: Recuperar l'estat del joc
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }
}
```

Els givens modifiquen l'estat del joc usant directament
els @Service. És preferible encapsular-los en serveis
explícits, com GivenGameService o GivenCardService. 
Aquests setejen l'estat, i esborren coses antigues si 
calgués. Per exemple: 
GivenCardService.givenCards(3, "Villager")
s'assegura que hi ha 3 i tan sols 3 cards de nom Villager.
Si n'hi ha més esborra fins a tenir tres, si hi ha menys en
crea.

Els givens recuperen l'estat del joc simulant una crida
de reload del frontend: "/api/v1/game". En la 
implementació del test del frontend, es farà un 
await waitForReload() per sincronitzar els canvis. 
Malgrat no ser necessària aquesta segona part, 
és molt recomanable per evitar problemes estranys.

* **Pregunta**: Quina Classe.mètode cal cridar per
crear un stack de cartes en una possició
concreta al given?

*

### Regles d'implementació mètodes Context.acció

Les accions simulen accions concretes sobre el frontend.
El frontend, com a conseqüència farà una crida REST al
backend, i, per tant, l'haurem de simular amb el 
frontendSimulator. Sempre guardarem el resultat de la
crida simulada. Mai usarem cap altre @Service per
modificar l'estat del backend.

```java
    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        gameDTO = frontendSimulator.post("/api/v1/game/moon", null, GameDTO.class);
    }
```

En algunes rares ocasions, és possible que les accions
que aquestes es quedin dins del frontend i no afectin el 
backend. Si és el cas, no caldrà fer la crida al
frontendSimulator al test del backend. I el mètode
quedarà buit.

* **Pregunta**: amb quina URL cal cridar al frontendSimulator
per moure una carta?

*

### Regles d'implementació mètodes Context.should

Els shoulds comproven que l'estat resultat és correcte.
Ho fan des del punt de vista de la informació que ha
rebut el frontEnd, i, per tant, amb el gameDTO. 
Mai s'usarà cap @Service, ni cap simulació de crida 
REST per obtenir els valors.

Es recomana usar els mètodes d'utilitat proporcionats
pels diversos \*ListDTO per accedir al contingut del
gameDTO amb el tipat correcte.

Sempre hi haurà dues parts:
1. Obtenir les dades del gameDTO.
2. Fer l'assert de les dades.

```java
    public void thereShouldBeNSCards(int expected, String cardName) {
        // text:  * There should be 2 "Berry" cards.
        // codi: this.thereShouldBeNSCards(2, "Berry")

        // 1. Obtenir dades.
        var actual = findAllCard(gameDTO, byName(cardName)).size();
        // 2. Fer l'assert
        assertThat(actual).isEqualTo(expected);
    }
```

Les dades s'obtindràn sempre del DTO i Mai de cap Service.

L'assert es farà amb la llibreria truth de google:

  Veure: https://truth.dev

* **Pregunta**: Quina classe.mètode estàtic cal cridar
per obtenir tots els stacks?

*

### Given @Services

Igual que tenim serveis de codi de producció, tenim 
serveis que implementen la lògica en comú dels mètodes
dels contexts dels tests, i principalment s'orienten a
resoldre les necessitats d'inicialitzar l'estat.

Aquests són els Given\*Service:

* GivenGameService
* GivenCardService
* GivenStackService
* GivenIdeaService

### TipusListDTO

Els TipusListDTO són tipus que no són Singleton, que
ofereixen utilitats, funcions estàtiques, per obtenir
parts el gameDTO.

Exemples són:

* CardListDTO
* IdeaListDTO
* StackListDTO

El cas de l'StackListDTO no correspon a cap tipus directe,
però ofereix una utilitat per retornar DerivedStackDTO.
Aquests són reconstruccions de les piles, tal com les veu
el frontend, i contenen CardDTOs.

La majoria dels mètodes d'utilitat suporten una versió
amb Predicate per filtrar el resultat i fer codi més
concís i fàcil de llegir.

* **Pregunta**: Quina crida he de fer per obtenir
la llista completa d'idees d'un gameDTO?

* 

### HasName, HasNames, HasPosition

Són interfícies que formen part del codi de producció,
paquet de les utilitats. Aquestes interfícies es recolzen
en classes com Names o Positions. Serveixen per fer,
quèries ràpides creant Predicates, ex:
Names.byName("Villager").

Addicionalment, Names, permet crear una llista de 
noms de forma senzilla, i fàcil de llegir. S'usa
freqüentment per crear stacks. Ex:
Names.byName(2, "Berry").and(1, "Militia").

* **Pregunta**: Quina crida he de fer per obtenir
totes les cartes a la posició 1 del gameDTO?

* 



