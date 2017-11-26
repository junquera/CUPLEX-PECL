10 INPUT "Cual es tu nombre?: "; U$
20 PRINT "Hola "; U$
30 INPUT "Cuantas estrellas quieres?: "; N
40 LET S$ = ""
50 FOR I = 1 TO N
60 LET S$ = S$ + "*"
70 NEXT I
80 PRINT S$
90 INPUT "Quieres mas estrellas? "; A$
120 IF A$ = "S" THEN GOTO 30
130 PRINT "Adios !! "; U$
140 END