10 PRINT "Cual es tu nombre?: "
20 INPUT U$
30 PRINT "Hola "; U$
40 PRINT "Cuantas estrellas quieres?: "
50 INPUT N
60 LET S$ = ""
70 FOR I = 1 TO N
80 LET S$ = S$ + "*"
90 REM ya no TENGO PROBLEMAS PARA LEER EN INT DEL PRINCIPIO DE NEXT
91 NEXT I
100 PRINT S$
110 PRINT "Quieres mas estrellas? "
120 INPUT A$
130 IF A$ = "S" THEN 30
140 PRINT "Adios !! "; U$
150 END