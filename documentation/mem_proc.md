#

## Introducción



## Analizador léxico

Sólo he tenido que escribir dos expresiones regulares para el análisis léxico: para obtener los identificadores y para obtener los Integer. Estos son sus AFD:

- Identificador     `[A-Za-z][A-Za-z0-9]*`



- Integer con signo `[+-]?[0-9]+`



No he empleado estados léxicos porque sólo utilizo el análisis léxico para generar los token, y no dependen de su contexto, así que todo el trabajo de analizar la estructura del programa es del análisis sintáctico.

## Analizador sintáctico

El analizador sintáctico tiene una gramática sin ambigüedades ni conflictos.

### Resolución de ambigüedades

La gramática tenía dos tipos de ambigüedad:

- Relativa a las operaciones matemáticas: Resuelto dando el siguiente orden de precedencia: `*` > `/` > `+` > `-`.

- Relativa a las operaciones booleanas: Como `AND` puede equivaler a una operación multiplicación y `OR` a una de suma, he seguido el mismo orden que con las operaciones matemáticas: `AND` > `OR`.

### Resolución de conflictos

Los conflictos tipo desplazamiento/reducción y reducción/reducción los he resuelto construyendo la gramática evitando que el conjunto *Primero* del conjuntos *Siguiente* de un elemento completo pudiese ser igual. Esto ha llevado a que no consiguiese extraer de un identificador dos tipos de elementos distintos, y sólo he conseguido que el análisis semántico extraiga de variables los enteros, sin funcionar con las variables booleanas.

## Analizador semántico

Para el analizador semántico he programado varias entidades e interfaces en Java. Todas ellas se encuentran en el archivo `Programa.java`. Principalmente, son las siguientes:

- **Statement** (interfaz): Interfaz para abstraer las lineas de código del cuerpo. He creado una clase para cada *statement*.

- **Condition** (interfaz): En condition agrupo todas las entidades generadas por *condition*. En función de la estructura de la gramática, reciben unos elementos u otros, pero todos comparten el método `getValue()` que posteriormente nos permitirá verificar los tipos.
- Tabla con tres columnas analisis no terminales.

### Comprobación dinámica de errores:

  La hago en división por cero, variable no inicializada o error de tipos al leer (read).

## Errores

No he conseguido leer los booleanos de identificador.

## Salida

- [x] Lista de tokens

- [x] Volcado de tabla de símbolos

- [ ] Reglas de análisis sintáctico utilizadas.

- [x] Listado de errores.

- [ ] Árbol de análisis sintáctico.
