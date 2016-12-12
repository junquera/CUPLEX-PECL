# PECL Procesadores de Lenguaje: Construcción de un analizador de un lenguaje imperativo

**Alumno** `Javier Junquera Sánchez`

**Correo** `<javier.junquera.sanchez@gmail.com>`

## Descripción del diseño

He escrito un archivo *.lex* en el que defino las expresiones regulares que servirán para generar los tokens. El archivo *.cup* contiene las gramáticas del lenguaje para poder construir el programa, y he añadido además un paquete de Java llamado *entidades* que contiene las clases auxiliares que he tenido que ir utilizando para el análisis Sintáctico/Semántico.

## Diseño del analizador léxico

El analizador léxico esta definido como un analizador para integrar con *cup*, que va guardando el número de línea y columna que va analizando y contiene un ArrayList "extra" en el que iré guardando los tokens para mostrar el análisis final:

``` java
ArrayList<String> tokens = new ArrayList<String>();
```

 La primera expresión regular que analiza el analizador léxico es la de cadenas vacías, implementada con la expresión *WhiteSpace*:

``` java
LineTerminator = \r|\n|\r\n

WhiteSpace     = {LineTerminator} | [ \t\f]
```

Así, nada más empezar, limpio los caracteres que no me servirán.

Después paso a definir cada uno de los tokens según la especificación. Cada uno de ellos añade la cadena correspontiente a su token a la lista de tokens y devuelve un `Symbol` para que pueda ser interpretado después por el analizador sintáctico.

Merecen especial atención los tokens `INTEGER`, `BOOLEAN` e `IDENTIFIER`; que además de estar definidos con expresiones regulares en la cabecera para simplificar el código:

``` java
IDENTIFIER=[A-Za-z][A-Za-z0-9]*
INTEGER=[+-]?[1-9]*[0-9]
BOOLEAN=true|false
...
{INTEGER} { tokens.add("integer"); return new Symbol(sym.INTEGER,...
{BOOLEAN} { tokens.add("boolean"); return new Symbol(sym.BOOLEAN,...
{IDENTIFIER} { tokens.add("ide(" + yytext() + ")"); return new Sy...
```

Devuelven además del `Symbol` el valor de la cadena que leen. Como puede verse, para evitar la ambigüedad entre mayúsculas y minúsculas, todos los identificadores son pasados a mayúsculas con `.toUpperCase()`.

Termino con que para cualquier caracter (`.`) imprima un mensaje de error indicando la línea y la columna:

``` java
. {
	System.err.printf("Hay un error léxico en la columna %d de ..."...
}
```

En la cabecera he añadido una declaración de fin de fichero (`%eof`) para que cuando termine el análisis imprima el resultado del análisis léxico.

## Diseño del analizador sintáctico

El analizador sintáctico se inicia con un objeto `Yylex` con el que analizará los tokens. Defino como símbolos terminales los tokens (en sí) y como no terminales los resultados de la gramática que tendrán que ser "parseados" en una clase de java.

``` java
// Definición de la clase de cada uno de los no-terminales

non terminal Programa program;
non terminal HashMap<String, Variable> vars;
non terminal HashMap<String, Variable> var;
non terminal HashMap<String, Variable> var_identifier;
non terminal Character type;

non terminal ArrayList<Statement> statements;
non terminal Statement statement;

non terminal AssignStatement asign_statement;
non terminal IfStatement if_statement;
non terminal RWStatement read_statement;
non terminal RWStatement write_statement;
non terminal WhileStatement while_statement;

non terminal Object value;

non terminal Boolean expr_booleana;
non terminal Boolean termino_booleano;

non terminal Integer termino_entero;

non terminal Integer comparation;
non terminal Integer operacion;
```

Como la definición del programa se tiene que hacer al principio del archivo he añadido la siguiente sentencia:

``` java
start with program;
```

En la gramática de `programa` defino las gramáticas que se analizaran entre su definición y el token `END`. El código *java* añade las variables en la tabla de símbolos que he definido como un *HashMap* para que no se repitiesen las claves (los identificadores de las variables) y la lista de sentencias, que he definido como un *ArrayList* para añadirlas secuencialmente y poder leerlas después.

La mayor parte del trabajo que he desarrollado en este archivo ha sido parte del analizador semántico, por lo que lo explicaré más tarde. Lo más complicado del analizador léxico ha sido definir las clases java que identificarían cada una de las entradas que leo. Paso a comentar este trabajo dividido principalmente en dos bloques: **variables** y **sentencias**.

Como no sabía para que servían y no quería "inventarme" nada, he omitido los tokens `skip` y `<>`.

### Variables

Las variables comienzan siendo un objeto de tipo *Variable* con dos parámetros: **identificador** y **tipo**. Después he creado tres clases que heredan de esta: **Int**, **Bool** y **Pseudo** (para los enteros, los booleanos y el identificador del programa, respectivamente).

### Sentencias

Todas las sentencias heredan de la clase **Statement**, y son las siguientes:

- AssignStatement: Esta clase contiene un objeto de tipo variable que posteriormente será analizado por el analizador semántico para comprobar su concordancia con la variable previamente definida en la tabla de símbolos.

- IfStatement: Las sentencias *if* tienen tres parámetros. El primero es la condición, con la que he tenido varios problemas. Además tiene dos listas de objetos *Statement*. La primera corresponde a las sentencias que se ejecutarán si se cumple la condición y la segunda corresponde al *else*. Como el bloque else no siempre existirá he dividido la definición de *if_statement* en dos definiciones:

``` java
if_statement ::= 	IF expr_booleana:e THEN statements:ss END IF SEMI_COLON
					{:
						...
					:}
					 | IF expr_booleana:e THEN statements:tss ELSE statements:fss...
					{:
						...
					:}
			 ;
```

Así, si no aparece un *else*, símplemente queda vacía la segunda lista de sentencias.

- WhileStatement: Al igual que *if*, contiene una condición y una lista con las sentencias que se ejecutarán mientras se cumpla la condición.

- RWStatement: Esta clase tiene un parámetro tipo y un parámetro variable. El tipo puede ser *r* para sentencias tipo `READ` y *w* para sentencias de escritura.

He creado una excepción que he llamado *SemanticException*. Se eleva cada vez que encuentro un error irresoluble y para la ejecución del programa.

### Problemas encontrados

El principal problema (y más grave) que he tenido ha a la hora de utilizar los identificadores de las variables para leer su valor en las expresiones booleanas. Como la tabla de símbolos se define en la gramática de *programa*, en las subsiguientes gramáticas no tengo acceso a ella, y no he conseguido dar con una solución que las guardase hasta el final. Este problema se arrastra a las sentencias *if* y *while* no permitiéndome definir bien las sentencias que se ejecutarían en cada caso. Habría sido fácil de solucionar si el analizador semántico fuese independiente o estuviese en otro nivel, pero al estar integrados no habría podido analizar el tipo (entero o booleano) del elemento correctamente.

Por ello, no he conseguido crear el árbol de análisis sintáctico. En su lugar, cuando termina de analizarse el programa imprimo el resultado del análisis.

Tampoco he conseguido mostrar la linea de los errores ni recuperarme de ellos en CUP. Sí que he visto que definiendo en las gramáticas una sentencia *error* lo hace automáticamente, pero no he conseguido encajarlo en mi código.

## Diseño del analizador semántico

El analizador semántico lo he utilizado principalmente para estudiar las variables introducidas e impedir: **a)** Que se introdujesen dos (o más) variables con un mismo identificador y **b)** que se asignase un valor no correspondiente al tipo de la variables (por ejemplo, que no se le asignase un entero a una variable de tipo booleano, o viceversa).

Parte de la definición del analizador semántico se encuentra dentro del archivo *cup*. En la definición de las distintas clases, he añadido más, aunque casi todas se encuentran también en la clase *Programa*.

Hasta aquí también he arrastrado mi problema con los identificadores de las variables en las expresiones booleanas. De hecho, si la implementación del analizador semántico la hubiese desarrollado exclusivamente en el programa, habría podido desarrollar el resto de otra forma.

## Diseño de la tabla de símbolos

Como he comentado anteriormente, la tabla de símbolos se encuentra almacenada en el *HashMap* de *Programa*, y se almacena con la clave del identificador de la variable. El objeto variable ya contiene el tipo y el valor de la variable.


## Conclusiones

La utilización de herramientas como *CUP* y *FLEX* simplifica enormemente la tarea a la hora de realizar un compilador. Además, el hecho de poder estructurar el análisis del código fuente de esta forma lo hace mucho más legible y actualizable.

La parte más ardua de este trabajo consiste en definir correctamente las entidades que servirán para abstraer los elementos de la gramática, más que definir la gramática en sí, que también resulta (relativamente) sencillo gracias a estas librerías (de hecho, podría ser automatizable).
