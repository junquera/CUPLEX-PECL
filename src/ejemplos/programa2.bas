0 REM Error en if (linea 140)
10 PRINT "Cual es tu nombre?: "
20 INPUT U$
30 PRINT "Hola "; U$
40 PRINT "Cuantas estrellas quieres?: "
50 INPUT N
60 LET S$ = ""
65 DIM A(1)
70 FOR I = 1 TO N
80 LET S$ = S$ + "*"
90 NEXT I
100 PRINT S$
110 PRINT "Quieres mas estrellas? "
120 INPUT A$
130 IF A$ = "S" THEN 30
135 DEF FNA(X) = X + 2
140 IF N > FNA(2) THEN A
140 PRINT "Adios !! "; U$
150 END
