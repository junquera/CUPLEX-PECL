# PECL Procesadores de lenguaje

## Introducción



## Analizador léxico

Sólo he tenido que escribir dos expresiones regulares para el análisis léxico: para obtener los identificadores y para obtener los Integer. Estos son sus AFD:

- Identificador     `[A-Za-z][A-Za-z0-9]*`

![AFD ID](identifier_afd.svg)

- Integer con signo `[+-]?[0-9]+`

![AFD INT](integer_afd.svg)


No he empleado estados léxicos porque sólo utilizo el análisis léxico para generar los token, y no dependen de su contexto, así que todo el trabajo de analizar la estructura del programa es del análisis sintáctico.

## Analizador sintáctico

Para realizar el analizador sintáctico utilizamos la clase `parser` de *cup*, que implementa un analizador **LALR(1)**. Verifica que comience siempre con la definición de *programa*:

``` java
start with programa;

programa ::= PROGRAM IDENTIFIER:id IS
                var_init_list:var_list
             BEGIN
                statement_list:stmt_list
             END {:
                var_list.put(id, new PseudoIdentifier(id));
                Programa p = new Programa(var_list, stmt_list);
                p.test();
             :};
```

También verifica que el programa a analizar se introduzca como parámetro en la ejecución y que su extensión sea *.program*.Tiene una gramática sin ambigüedades ni conflictos. Únicamente lanza 2 warning al ejecutar la librería **CUP**, pero no son por fallo de la gramática. No he utilizado los tokens `<>` ni `skip` porque no sabía donde usarlos.

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

- **Expression** (interfaz): Diseñada con la misma filosofía que **Condition** pero orientada a valores enteros. Con **Expression** también podemos extraer el valor de los identificadores enteros.

- **Programa** (clase): Contiene la tabla de símbolos y una lista con todos los *statements*. También tiene el método *test()* que, ejecutado tras el análisis sintáctico genera el programa y evalúa los errores semánticos.

### Comprobación estática de errores

- Comprueba errores de tipo.

- Comprueba que las variables utilizadas a lo largo del código estén declaradas.

- Evita declaración de variables repetidas.

### Comprobación dinámica de errores:

- Evita la división entre 0.

- Comprueba que el valor introducido en un `read` concuerde con el tipo de la variable.
