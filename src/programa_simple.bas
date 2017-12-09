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
