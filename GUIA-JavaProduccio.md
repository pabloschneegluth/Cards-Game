<!-- TOC -->
- [Java Producció](#java-producció)
  - [Singleton](#singleton)
  - [@Service](#service)
  - [@Repository](#repository)
  - [@RestController](#restcontroller)
  - [Injeccio de col·leccions i @Component](#injeccio-de-colleccions-i-component)
  - [Patró Step](#patró-step)
  - [Tipus i dades flexibles](#tipus-i-dades-flexibles)
<!-- /TOC -->

## Java Producció

El codi Java de producció és a `src/main/java/`,
l'aplicació principal s'arrenca amb:

```java
@SpringBootApplication
public class ClassroomCardsGame2022Application {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomCardsGame2022Application.class, args);
    }

}
```

Podeu veure que la classe té una anotació: `@SpringBootApplication`.

Aquesta anotació li diu a Spring que arrenqui
l'aplicació amb el main d'aquesta classe.

**Pregunta**: Quines altres anotacions hi trobes que anotin
classes o interfícies?

* @Service
*

### Singleton

Les anotacions @Service, @Component, @Repository, i
@RestController defineixen les classes com a Singletons 
amb 's' minúscula.

Amb Spring, les instàncies de Singletons no s'accedeixen
mitjançant statics, sinó mitjançant injecció de dependències
a través del constructor.

Per exemple, el constructor del `CardService`, injecta dos
singletons:

* cardRepository
* tagService

> Observa que el que s'injecta es guarda en atributs `final`
> i que els constructors no fan feina: 
> tan sols es guarden el que s'ha injectat.

**Pregunta**: Quins singletons s'injecta la classe 
`EndMoonStep_900_HarvestIdea`?

* cardFactory
*

### @Service

Els service són classes, estil Facade, que donen accés a
operacions amb la lògica de negoci.

Exemples que podem trobar dins del package game són:

* GameService
* GameFactory

El GameService és la façana principal per accedir a la 
lògica de negoci del joc. Tanmateix, no és una façana
completa: hem extret part de la lògica a 
serveis més especialitzats.

El GameFactory és la part del servei que s'ha especialitzat
en la creació de partides. En aquest cas disposa d'un mètode
per crear una partida única. (En aquest joc tan sols hi ha
una partida en tot moment).

Així doncs, les especialitzacions son:

* GameService -> façana principal
* GameFactory -> creació de games

**Pregunta**: Quins @Service hi ha al paquet Card?

* CardService
*

**Pregunta**: En que s'ha especialitzat cada un?

* CardService -> façana principal
*

### @Repository

Els repositori són interfícies que descriuen com 
accedir a les bases de dades.

Podem trobar els següents repositoris:

* CardRepository
* GameRepository
* IdeaRepository

Per exemple, el CardRepository té el següent codi:

```java
@Repository
interface CardRepository extends JpaRepository<Card, String> {

    List<Card> findAllByName(String name);
    List<Card> findAllByNameAndZindex(String name, int zindex);
    List<Card> findAllByPositionOrderByZindexAsc(int position);
    List<Card> findAllByOrderByPositionAsc();
    boolean existsByPosition(int freePosition);
}
```

A destacar que:

1. És interfície, no classe. Els repositories mai són 
classes. El codi l'implementa Spring per nosaltres. Per tant, 
quan injectem un CardRepository, Spring crearà una classe 
que implementi els mètodes i el podrem utilitzar. No hem
d'implementar cap lògica per accedir a la base de dades.

2. No és públic, és package private. Per evitar problemes 
de desar dades, els repository són privats al paquet, i tan 
sols poden ser accedits pels serveis en el mateix paquet.

3. Estén de JpaRepository. Això fa que tingui tota una 
sèrie de mètodes ja disponibles, com findById o save.
Es parametritza amb dos genèrics, en aquest cas
<Card, String>, on el primer és el que es desa en la
base de dades (la taula), i el segon és el tipus de la 
primary key.

  Veure: https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html

4. Spring crea la taula a la base de dades automàticament.
Com sap quin és el tipus de què es desa a la base de dades 
(Card en aquest cas), i com sap quins són els camps que té
(atributs), pot crear les taules amb les columnes amb els
tipus correctes.

5. Com coneix la taula, sap com fer les queries. Si 
s'afegeixen mètodes a la interfície, ens farà les queries
automàticament per allò que es demana. L'IntelliJ dona 
suport per autocompletat per escriure els mètodes, i aquí 
disposeu del link a la documentació. No hem d'escriure 
nosaltres l'SQL. 

  Per exemple:

  * findAllByNameAndZindex busca totes les cartes amb 
els valors dels atributs name i zindex corresponents 
als arguments. 

  * findAllByPositionOrderByZindexAsc busca totes les
cartes amb posició corresponent a l'argument, i les retorna 
ordenades per zindex en ordre ascendent.

  Veure: https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation

> Stacks: no existeix repositori d'stack perquè no existeix 
> cap entitat stack. Tan sols és una classe (abstracció) 
> auxiliar per ajudar a tractar amb piles de cartes.

**Pregunta**: Del GameRepository, anomena com a mínim quatre 
mètodes que pots invocar:

* save 
*

**Pregunta**: Quin seria el nom del métode de Idea amb el 
nivell donat per l'argument i xp  superior al valor del 
segon argument?

* 

### @RestController

Els rest controllers són les classes que permeten tractar
les crides a l'API rest.

Quan Spring descobreix que hi ha classes amb API rest,
aixeca un servidor web, i tracta les peticions HTTP entrants
per executar els rest controllers corresponents. El que fa 
és crear una instància singleton "s" minúscula del rest 
controller, i cridar al mètode corresponent cada cop que es 
rep una petició.

Les peticions http hi ha de 2 tipus principals: GET i POST.
La petició GET és la que fa el navegador quan escrivim
un URL i carreguem la pàgina. La petició POST és quan omplim 
un form i aquest envia les seves dades al servidor. 
Actualment, i com a part del protocol REST, aquests dos 
tipus de peticions s'usen en programes per a comunicar 
serveis i backend-frontends entre ells.

A més de l'anotació @RestController, trobem altres
anotacions associades en aquestes classes, els seus mètodes,
i els arguments:

* @RequestMapping("url") associa el controlador a les
peticions HTTP que arriben a la url donada. Per exemple,
si aixeca el servidor a http://localhost:8080, si la "url"
és "/api/v1/game/cards", es pot accedir a través d'una
petició a "http://localhost:8080/api/v1/game/cards".

* @GetMapping("url/opcional") associa un mètode a contestar 
una petició de tipus GET. Quan es rep una petició GET a 
l'URL del @RequestMapping executa el mètode amb la anotació. 
Si tingués una url, llavors seria la concatenació, ex:
@RequestMapping("/api/v1/game") i @GetMapping("/moons") 
executaria el mètode quan es rebés una petició GET a
"http://localhost:8080/api/v1/game/cards".
El resultat del mètode, se serialitza a JSON, i es retorna
com a body en la resposta a la petició.

* @PostMapping("url/opcional") associa un mètode a contestar 
una petició de tipus POST. Es comporta igual que el 
@GetMapping, excepte que a més ara pot rebre un 
@RequestBody.

* @PathVariable String nomVariable, anota un argument de 
tipus String per rebre dins un fragment de l'URL. Per 
exemple, donat @RequestMapping("/api/v1/game/cards") i 
@GetMapping("/{cardId}") i @PathVariable String cardId, quan 
es faci el GET de
"http://localhost:8080/api/v1/game/cards/villager1",
dintre de cardId trobarem "vilalger1".

* @RequestBody Tipus nomBody, anota un argument de tipus 
Tipus, per rebre en ell el contingut del body de la petició 
POST. Spring automàticament parseja com a JSON el body, i el 
guarda a l'argumet nomBody.

Exemple, en CardController.moveCard té:

* @RequestMapping("/api/v1/game/cards") 
* @PostMapping("/{cardId}/move")
* @PathVariable String cardId
* @RequestBody MoveCardDTO moveCardDTO
* Retorna GameDTO

Quan el frontend fa una petició de tipus POST a:

* http://localhost:8080/api/v1/game/cards/villager1/move
* amb body { "position": 1, "zindex": 3 }

Parseja el body, i crea una instància de MoveCardDTO i 
l'inicialitza amb el contingut del body. Spring executa el 
mètode moveCard de CardController amb arguments "move" i el 
moveCardDTO que ha parsejat del body. Espera al resultat del 
mètode, serialitza el gameDTO a JSON, i li retorna a 
l'usuari.

**Exercici**: Arrenca la aplicació Spring (demana a 
l'IntelliJ que executi ClassroomCardsGame2022Application),
obre el navegador, i escriu la URL:
http://localhost:8080/api/v1/game.
Veurás que apareix a la pàgina un objecte JSON.

**Pregunta**: L'objecte JSON, te dos camps
`{ "camp1": [...] , "camp2": [...] }`, quins són
els dos camps?

* 
*

**Pregunta**: Quin @RestController ha usat
Spring per obtenir la resposta?

* 

**Pregunta**: Quin métode ha executat d'aquell 
@RestController? 

* 

**Pregunta**: El JSON que veiem de resposta, és
del tipus que retorna el métode. De quin tipus és?

* 

### Injeccio de col·leccions i @Component

Fins ara hem vist que Spring injecta al constructor
instàncies del tipus que s'han demanat, per exemple:

```java
    public GameController(GameService gameService, GameFactory gameFactory, MoonService moonService, GameDTOFactory gameDTOFactory) {
        this.gameService = gameService;
        this.gameFactory = gameFactory;
        this.moonService = moonService;
        this.gameDTOFactory = gameDTOFactory;
    }
```

injecta 4 singletons de 4 classes diferents.

Però Spring permet injectar alhora tots els singletons
de classes que implementin la mateixa interfície i estiguin 
anotats per @Component. Per exemple:

```java
    public MoonService(List<EndMoonStep> endMoonSteps) {
        this.endMoonSteps = Steps.from(endMoonSteps);
    }
```

En aquest cas, MoonService demana a Spring que li injecti 
una instància de cada classe que implementi EndMoonStep. 
Si els busquem, aquestes són:

* EndMoonStep_100_EatersEatFood
* EndMoonStep_900_HarvestIdea
* EndMoonStep_900_SeedIdea
* EndMoonStep_900_WoodsStrollIdea

Per tant, Spring injectará 4 instàncies d'EndMoonStep, una 
per cada classe amb @Component.

El motiu d'això és poder invertir les dependències entre els 
diversos paquets de l'aplicació. 

En aquest cas, moon és el paquet que regula els cicles de la 
lluna, i que cal que executi tot el que succeeix quan acaba 
la lluna. Però no volem que hi depengui, és a dir, que 
conegui quines accions calen fer.

Per exemple, moon executarà 4 accions, dels paquets de
tags i d'idees. Però moon no sabrà que existeixen 
aquests paquets. Moon pública la interfície EndMoonStep
que implementaran els altres, i això farà que s'afegeixin
automàticament. 

D'aquesta manera, podem estendre el comportament d'endMoon, 
sense haver de tocar el seu codi. Cada cop que afegim una 
cosa més a fer, tan sols caldrà afegir una classe més que 
implementi EndMoonStep i anotar-la amb @Component.

**Pregunta**: Si MoonService s'injecta la llista 
d'interficies @Component que satisfan EndMoonStep, quines 
altres interfícies @Component hi ha que s'injecten en altres 
@Services?

*

### Patró Step

Per unificar com funciona la injecció de @Component en 
llistes, s'ha creat el "patró Step". 

Per exemple, si mirem l'EndMoonStep, veurem que  quan 
s'acaba la lluna hem de fer 4 passos:

* Els que mengen mengen menjà,
* Es fa la collita (Harvest),
* Es planten llavors (Seed),
* I es passeja pel bosc (Stroll)

L'ordre d'aquests passos és rellevant. Per exemple, si el 
vilatà necessita un de menjar per sobreviure, no hi ha 
menjar en acabar la lluna, mor de gana, però si primer fa la 
collita, llavors pot menjar el que  acaba de recollir. 
L'ordre dels passos defineix part de les regles del joc, 
aquí assumim que el vilatà necessita menjar abans de fer cap 
esforç.

Però l'ordre no tan sols és rellevant per les regles del 
joc, si no perquè tot funcioni correctament. Un pas pot 
necessitar que un altre s'executi abans, i fallar si no és 
així.

El Step (del paquet util) s'ha dissenyat per tractar amb 
això. Assumeix que cada pas té un ordre, i l'ordre és 
determinat pel nom de la classe.

És per això, que totes les implementacions d'interfícies
que deriven de Step, comencem pel nom de la interfície,
i continuen per tres xifres: la prioritat. Ex:

* EndMoonStep_100_EatersEatFood
* EndMoonStep_900_HarvestIdea
* EndMoonStep_900_SeedIdea
* EndMoonStep_900_WoodsStrollIdea

El _100_ i _900_ expressa l'ordre, més petit abans,
i en cas d'empat, usa el nom. Aquests steps
s'executen en l'ordre anterior.

**Pregunta**: En cas del GameDTOFactoryStep, quines
implementacions n'hi ha i en quin ordre s'executaran?

* 

Malgrat Spring tenir @Order per solventar això,
s'ha optat per utilitzar el nom de la classe per fer més
visible aquesta relació.

La classe que fa l'ordenació, i prepara per executar
els passos és Step. Podem veure el seu ús al codi de
moon Service:

```java
@Service
public class MoonService {

    private final Steps<EndMoonSettings> endMoonSteps;

    public MoonService(List<EndMoonStep> endMoonSteps) {
        this.endMoonSteps = Steps.from(endMoonSteps);
    }

    public void endMoon() {
        endMoonSteps.execute(new EndMoonSettings());
    }
}
```

Steps.from crea un objecte Steps de la llista, i s'assegura
que estigui ordenada segons el criteri explicat.

El mètode Steps.execute, executa tots els steps que
hi havia en la llista, en ordre, i li passa un objecte 
de tipus Settings a tots ells. 

Cada tipus d'step tindrà un tipus de Settings associats
per ajudar a compartir informació entre passos.

**Pregunta**: El GameDTOFactoryStep usa en l'execute
un GameDTOFactorySettings. Quina dada guarda en el
settings que tots els passos de GameDTOFactoryStep
poden accedir?

* 

### Tipus i dades flexibles

Com a resultat d'invertir la dependència entre paquets i la
la seva execució, ens trobem que hi ha objectes que no 
podem saber els tipus del que es guarda dins. 

Això ho podem trobar a GameDTO i als diferents Settings.

Per exemple el GameDTO, de Game, no coneix els paquets
de cards ni d'idees. Per tant, malgrat que GameDTO inclogui
informació dels cards i idees, aquest no pot saber que 
existeixen:

```java
// Codi incorrecte: No respecte inversió de dependències
public class GameDTO {

    private List<CardDTO> cards;
    private List<IdeaDTO> ideas; 

    // constructor...

    public List<CardDTO> getCards() { ... }
    public List<IdeaDTO> getIdeas() { ... }
}

Aquest codi depèn de card i idea, no tan sols perquè necessita
conèixer els tipus, si no també perquè cada cop que afegim 
més paquets i més conceptes, caldrà anar modificant GameDTO.

Per evitar això, GameDTO passa ser agnòstica, i canvia per ser
així:

```java
// Codi correcte: compleix Open Close Principle
public class GameDTO extends TreeMap<String, Object> {
  public <T> T getField(String fieldName, Class<T> fieldClass);
}
```

En aquesta implementació, GameDTO no coneix ni idea
ni card, i es pot estendre sense canviar el codi. Si algú
vol obtenir la llista d'idees, ara pot fer:
`gameDTO.getField("idees", IdeaListDTO.class)`.

> Nota: El IdeaListDTO existeix perquè el java no fa classes
> pels generics, amb el qué List<Idea>.class 
> i List<Object>.class són el mateix.


**Pregunta**: Quines classes criden GameDTO.getField?

* 


> Incís: GameDTO estén TreeMap per respectar el JSON de sortida.
> Sense aquesta restricció, és preferrible que sigui un camp
> com s'ha fet a Settings.

