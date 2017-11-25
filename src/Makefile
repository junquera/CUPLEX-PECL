all: lex cup
	echo "Lex + Cup"

lex:
	jflex main.flex -d ../bin

cup:
	jcup main.cup -destdir ../bin

clean:
	rm parser.java sym.java BASICLexer.java
