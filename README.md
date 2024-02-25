# Kata "Software de bolera"

En esta kata se expone un problema en el que se debe implementar un algoritmo para el cálculo de la puntuación de una bolera.
En este fichero encontrará una explicación de las normas y se plantearán casos de prueba de escenarios que el software debe soportar.
Es un ejemplo perfecto de como TDD (Test-driven-development) nos puede ayudar a programar enfocando el desarrollo en ciclos de:

- Escritura de test: implementar test automatizados para probar la funcionalidad a desarrollar
- Escritura de código: implementar un código que implemente esa funcionalidad y pase los tests
- Refactorización: mejorar el código en diseño y comprensividad con buenos nombres y sin código duplicado.

Puedes resolver el problema en el lenguaje de programación que prefieras.
En caso de elejir java tienes en la carpeta "java-only-tests" los test implementados que puedes comentar e ir descomentando poco a poco según avances con la implementación. Si usas otro lenguaje de programación puedes traducir los tests fácilmente.

## Reglas

### Regla básicas

- Se tira 10 rondas de 2 tiradas máximo cada ronda.
- Al principio de cada ronda se colocan los 10 bolos.
- Cada bolo tirado suma un punto.

![Colocación bolos](resources/img/bolos.png?raw=true "Bolos")

### Tests

Se recomienda usar TDD y empezar escribiendo uno a uno los tests.
La entrada al “algoritmo” serán el nº de bolos tirados y la salida la puntuación.
Tests básicos:

- Tirar siempre un bolo (10 rondas x 2 tiradas = 20 tiradas => 20 bolos)
  ![Tirada siempre un bolo](resources/img/tirada_todo_1.png?raw=true "Siempre un bolo")
  Este juego lo escribiremos por abreviar como "20x1" (<nº veces>x<nº bolos tirados>)
  20x1 -> 20 puntos

- Tirar siempre 0 bolos:
  20x0 -> 0 puntos

- Tirar 10 veces 3 y el resto 0:  
   10x3 & 10x0 -> 30 puntos

### Semipleno "/"

Si entre las 2 tiradas de la ronda tira los 10 bolos es un semipleno.  
Esto implica que te regalan como EXTRA para esta ronda tantos puntos como bolos tires en la siguiente TIRADA.
Tests:

- Semipleno suma la siguiente tirada:  
   5 & 5 & 3 & 17x0 -> 16 puntos
- No semipleno si los 10 puntos no son en la misma ronda:  
   0 & 5 & 5 & 3 & 16x0 -> 13 puntos
- Semipleno SOLO suma la siguiente tirada:
  5 & 5 & 3 & 17x1 -> 33 puntos

### Pleno

Si en la primera tirada de una ronda tira los 10 bolos es un pleno. No tirará la segunda tirada de la ronda.  
Esto implica que te regalan como EXTRA para esta ronda tantos puntos como bolos tires en la siguientes 2 TIRADAS.  
Tests:

- Pleno suma los 2 siguientes tiradas:  
   10 & 3 & 2 & 16x0 -> 20 puntos
- Si los 10 son en segunda tirada es semipleno:  
   0 & 10 & 3 & 2 & 16x0 -> 18 puntos
- Pleno SOLO suma las 2 siguientes tiradas:  
   10 & 3 & 2 & 16x1 -> 36 puntos

### Regla del final

La décima ronda puede tener más tiradas para saber los puntos extra de plenos o semiplenos.  
Si haces un pleno tendrás dos lanzamientos más.  
Si haces un semipleno tendrás un lanzamiento más.  
Los bolos tirados en las rondas extra solo valen como puntos extra del pleno o semipleno no como tirada propia.  
Tests:

- Pleno en última ronda da 2 tiradas extra:  
   18x0 & 10 & 1 & 1 -> 12 puntos
- Semipleno en última ronda da 1 tirada extra:  
   18x0 & 5 & 5 & 1 -> 11 puntos
- Todas las tiradas perfectas de 10 bolos (las 10 del juego más las 2 extra) consiguiendo la puntuación máxima:
  12x10 -> 300

![300 puntos](resources/img/300puntos.png?raw=true "300 puntos")

# Comentarios test Jonathan Baragaño

Como llevo poco tiempo realizando TDD con junit en JAVA (he empezado este mes), he comentado todos los test e intentaré resolverlos a mi manera para poder afianzar los conocimientos. Como se ha explicado, el flujo debe ser realizar un test que no se supere (red), luego implementar un método que consiga superar el test (green) y refactorizar el código. En estos comentarios iré comentando paso a paso todo lo que voy haciendo, así le puede servir como guía a aquellas personas que se están iniciando en TDD. En los comentarios añadiré para cada uno de los test el nombre del test (Red) y si hay algo que destacar que considere importante, en "Green" comentarios acerca de lo realizado para superar el test y en "Refactoring" si hay algo que destacar.

## Test peor juego posible (worstGame)

Es el equivalente a 20x0.
Voy a empezar realizando el primer test comprobando la peor puntuación posible que es 0. Para ello tienes 20 lanzamientos en los que no se ha logrado tirar ni un solo bolo. En este caso, la puntuación esperada en el test debe ser 0 y para ello en el método BowlingGame bastará con retornar un 0 en la puntuación.
**Red**: testWorstGame ;
**Green**: return 0 in getScore();
**Refactoring**: no hago nada por el momento.

## Test tirar un bolo en todo el juego (onePin)

En este test, vamos a considerar que durante 19 tiradas no se ha conseguido tirar ni un solo bolo y que en otra de las tiradas se ha tirado un bolo. Por lo tanto, la puntuación es 1.
**Red**: testOnePin;
**Green**: Simulamos haber tirado un bolo antes de la iteración.

```java
game.roll(1)
```

En la clase BowlingGame añado una variable tipo int que servirá para almacenar el valor de las tiradas. En el método roll y para que por el momento supere el test de la forma más sencilla posible el valor de count debe actualizarse en cada tirada sumándole el número de bolos tirados.:

```java
(count =+ pins)
```

Lo ejecutamos en el test:

```java
 (game.roll(pins))
```

y en el método getScore se retornará su valor.
En este caso, en el test, el método roll recibe como parámetro 1 (pins = 1), por lo tanto el valor de la variable count es 1 y ese es el valor que se debe esperar en el test.
**Refactoring**: no hago nada importante por el momento.

## Test tirar un bolo en cada tirada

Este test se puede resolver fácilmente con una pequeña modificación del test anterior ya que solo tenemos que hacer que en cada iteración del bucle for se sume 1 a la puntuación y esperar que el resultado tras 20 lanzamientos sea 20.
**Red**: testGameAll1
**Green**: hacemos que en cada tirada se consiga una puntuación de 1, para ello ejecutamos dentro del bucle for:

```java
 for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
```

La puntuación esperada debe ser 20

```java
Assert.assertEquals(20, score);
```

**Refactoring**: Podemos observar un bucle que ejecutamos una y otra vez, así que podemos implementar un método para el número de tiradas y así ahorraremos líneas de código. Este método lo llamaremos **rollMany** que recibirá como paramétros el número de lanzamientos (**times**) y los bolos derribados (**pins**) Por lo tanto, tenemos un nuevo método void que nos permitirá simular un lanzamiento el número de veces que queramos:

```java
private void rollMany(BowlingGame game, int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
```

De esta forma podremos refactorizar el código:

```java
@Test
    public void testGameAll1() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }

        int score = game.getScore();
        Assert.assertEquals(20, score);
    }
```

a:

```java
    @Test
    public void testGameAll1() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 1);

        int score = game.getScore();
        Assert.assertEquals(20, score);
    }
```

y podemos hacer lo mismo para los test anteriores que habíamos hecho simplificando su código.

## Verificar que no puedes tirar más de 10 bolos (toLargeRoll)

Como el número de bolos máximo que se puede derribar en un lanzamiento son 10, deberíamos testear que se cumpla este requisito.

**Red:** Para realizar esta verificación, creamos una instancia del juego de bolos y luego intentamos tirar 11 bolos en un solo lanzamiento llamando al método **roll** con valor de 11 como argumento. Como este movimiento no es válido en el juego, esperamos que el juego lance una excepción del tipo **IllegalArgumentException**.

**Green:** Una vez escrito el test, para conseguir que se supere, debemos añadir la excepción al método roll, por lo tanto, debemos implementar una sentencia de control que se ejecute en el caso de un lanzamiento superior a 10:

```java
 public void roll(int pins) {
        if (pins > 10) {
            throw new IllegalArgumentException();
        }
        count += pins;
    }
```

**Refactoring**: no hacemos nada más en este momento.

## Verificar que no hay lanzamientos con números negativos (negativeRoll).

Podemos realizar un test muy similar al anterior que lance una excepción si se derriban un número de bolos negativo, ya que esto no es posible.

**Red**: Podemos reutilizar el test anterior cambiando el argumento de roll a un número negativo, por ejemplo:

```java
game.roll(-1)
```

**Green**: Para pasar el test, bastará con modificar la condición en la sentencia de control para aquellas hipotéticas situaciones en las que se derriben bolos negativos.

```java
if(pins < 0 || pins > 10)
```

**Refactoring**: No hay modificaciones en este momento
