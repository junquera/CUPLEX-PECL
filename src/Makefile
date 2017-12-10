all: lex cup
	echo "Lex + Cup"

lex:
	../jflex/jflex/bin/jflex main.jflex

cup:
	../jflex/jflex/bin/jcup main.cup

clean:
	rm parser.java sym.java BASICLexer.java
