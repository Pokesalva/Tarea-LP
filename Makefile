JAVAC=javac
JAR=json-20240303.jar
SRC=main.java Javaling.java Agua.java Fuego.java Planta.java Dragon.java Jugador.java Entrenador.java Piso.java Objeto.java Movimiento.java Batalla.java Tipo.java DataManager.java
CLASSES=$(SRC:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JAVAC) -cp .:$(JAR) $<

run: all
	java -cp .:$(JAR) main

clean:
	rm -f *.class