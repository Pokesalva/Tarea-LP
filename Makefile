JAVAC=javac
SRC=main.java Javaling.java Agua.java Fuego.java Planta.java Dragon.java Jugador.java Entrenador.java Piso.java Objeto.java Movimiento.java Batalla.java Tipo.java
CLASSES=$(SRC:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

run: all
	java main

clean:
	rm -f *.class