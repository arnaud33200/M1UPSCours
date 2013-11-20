OPTION=-g
# OPTION=-Wall -g

all : exercice1.o exercice2.o
	gcc $(OPTION) exercice1.o -o exercice1
	gcc $(OPTION) exercice2.o -o exercice2

exercice1.o : exercice1.c
	gcc $(OPTION) -c $< 

exercice2.o : exercice2.c
	gcc $(OPTION) -c $< 