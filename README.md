# Memoria PECL Procesadores del Lenguaje

## Introducción

Se propone la creación de un sistema capaz de realizar las fases de análisis léxico, sintáctico y semántico de un compilador de BASIC (dialecto de `ECMA-55 Minimal BASIC`). Para ello utilizaremos las herramientas **JFLex** y **CUP**. Con **JFLex** crearemos un sistema encargado de la generación de los tokens y la eliminación de caracteres innecesarios (análisis léxico). Con **CUP** haremos un analizador LALR que se encargará de la generación de un árbol de derivación de la gramática que definamos (análisis sintáctico), el manejo de errores y de tabla de símbolos; y, finalmente, de verificar que se cumplen las especificaciones semánticas.

## Analizador léxico

- Diseño del Analizador Léxico: tokens, expresiones, autómata, acciones y errores.

### Especificaciones léxicas
 
Al principio iba a indicar las especificaciones léxicas con comentarios en el código del archivo `main.jflex` con el siguiente formato:

```java
/* EL [n]. {Descripción} */
```

Pero al aumentar la complejidad del código, considero necesario explicar algunas cosas de forma más detallada:

- EL 4. 

Para los identificadores de variable he decidido hacer el análisis a través del léxico, y que devuelva un token `VAR_NUM` para las variables numéricas (sólo una letra) y `VAR_TXT` para las variables de cadena.

- EL 5.

Para los número enteros (`Num_Entero= [+-]?{Digito}+`) devuelvo el token `INTEGER`. Tanto para los números reales (`Num_Real = {Num_Entero}\.{Digito}+`) como para los escalares (`Num_Escalar = {Num_Real}[E]{Num_Entero}`) devuelvo el token `FLOAT`, porque ví que java interpreta correctamente los valores del tipo `1.2E5` como valores de coma flotante.


- EL 6.

Sólo he programado que se pasen por alto los espacios en blanco correspondientes a tabulaciones y a espacios, porque los saltos de línea quería que generasen un token para el analizador sintáctico (las lineas del programa terminan cuando reciben el token `CRLF`).

- EL 7.

Aunque encontré la forma de que el programa pudiese leer el *EOF* con la expresión `\x00 | \x03`, ví que el propio *JFlex* ofrece el método `<<EOF>>` y me parecía mucho más limpio y seguro.

### Requisitos léxicos

- RL 1.

Para cualquier caracter no definido en las especificaciones léxicas devuelvo un token `ERROR`, que puede ser recogido por el analizador sintáctico. Así mismo, imprimo por pantalla la línea y la columna en la que ha sido encontrado.

- RL 2.

Definidos en el apartado [Autómatas](#aut)

- RL 3.

Definidos en el apartado [Uso de estados léxicos](#lex_state)

- RL 4.

Durante el análisis léxico, voy recogiendo todos los tokens en la estructura `List<BASICSymbol> tokens`. Para hacerlo, defino el nombre que tiene que tener el método que devuelve el siguiente token (`%function nextToken`) y escribo una función `next_token` (que es a la que llamará *CUP*) que hace de interfaz de `nextToken` guardando los tokens en la estructura mencionada antes de devolverlos.

Al terminar todos los análisis, el programa principal imprime todos los token obtenidos. Por ejemplo, para la entrada: 


```BASIC
0 REM programa_simple.bas
10 PRINT "Cual es tu nombre?: "
20 READ U$
30 DATA "Javier"
40 PRINT "Hola "; U$
50 PRINT "Cuantas estrellas quieres?: "
60 INPUT N
70 PRINT "Cuantas puntas tiene una estrella?: "
80 READ P
90 DATA 5
100 LET R = P * N
110 PRINT "Total:"; R ; " puntas"
120 PRINT "Adios !! "; U$
130 END
```

Obtendríamos la siguiente salida:

```
/**************************/
/** Análisis léxico     **/
/**************************/

[INTEGER(0), REM, CRLF, INTEGER(10), PRINT, 
STRING(Cual es tu nombre?: ), CRLF, INTEGER(20), READ, 
VAR_TXT(U$), CRLF, INTEGER(30), DATA, STRING(Javier), CRLF, INTEGER(40), 
PRINT, STRING(Hola ), PCOMA, VAR_TXT(U$), CRLF, INTEGER(50), PRINT, 
STRING(Cuantas estrellas quieres?: ), CRLF, INTEGER(60), INPUT, VAR_NUM(N), 
CRLF, INTEGER(70), PRINT, STRING(Cuantas puntas tiene una estrella?: ), 
CRLF, INTEGER(80), READ, VAR_NUM(P), CRLF, INTEGER(90), DATA, INTEGER(5), 
CRLF, INTEGER(100), LET, VAR_NUM(R), EQU, VAR_NUM(P), MUL, VAR_NUM(N), CRLF, 
INTEGER(110), PRINT, STRING(Total:), PCOMA, VAR_NUM(R), PCOMA, STRING( puntas), 
CRLF, INTEGER(120), PRINT, STRING(Adios !! ), PCOMA, VAR_TXT(U$), CRLF, 
INTEGER(130), END, CRLF, EOF, EOF, ]
```

### <tag id="lex_state">Uso de estados léxicos</tag>

He utilizado estados léxicos para generar los tokens `STRING` y `REM`.

- `%state STRING`

Para no capturar dentro del token las comillas del string, he creado un estado léxico al que se accede cuando se abren las comillas (`"`) y del que se sale (se vuelve a `YYINITIAL`) cuando se encuentran las de cierre, devolviendo el token con todo el contenido encontrado entre medias.

- `%state REM`

Como la declaración de que es una línea *REM* se produce después de el identificador de número de línea, si no introducía en el token (más bien, hago que el token se lo coma, porque no lo guardo) todo lo que hubiese hasta el salto de línea, el analizador sintáctico fallaba. Así que, ante el token `REM`, nos ponemos a pasar por alto cualquier entrada hasta el salto de línea.

### <tag id="aut">Autómatas</tag>



## Analizador sintáctico

La última línea tiene que tener salto de línea.

- Diseño del Analizador Sintáctico: gramática, demostración de que la gramática es adecuada y las tablas o procedimientos de dicho Analizador.

### Especificaciones sintácticas


### Requisitons sintácticos


### Gramática


## Análisis semántico

- Diseño del Analizador Semántico: Traducción Dirigida por la Sintaxis con las acciones semánticas.

### Especificaciones semánticas

## Tabla de símbolos
- Diseño de la Tabla de Símbolos: Descripción de su estructura y organización.

## Arbol de derivación
- Diseño del árbol de derivación: Descripción de su estructura y organización.

## Casos de prueba

- Diez casos de prueba y su salida. Deberá incluirse en la memoria un anexo con los diez casos listados. Dos de ellos serán correctos y los otros erróneos, de tal manera que permitan observar el comportamiento de la solución dada.
- Para uno de los ejemplos correctos, se incluirá el listado de tokens, la salida de las reglas aplicadas por el analizador sintáctico, el árbol de derivación y el volcado de la tabla de símbolos.
- Para los ejemplos erróneos se incluirá el mensaje o mensajes de error obtenidos.

### Errores

- [x] Hay un error en los bucles `for`. No sé cómo indicar la línea del `NEXT` sin incluir el entero del principio.

- [ ] Ver si hay una forma más limpia (por contexto a lo mejor) de arreglar el problema del `NEXT` en el `FOR`.
