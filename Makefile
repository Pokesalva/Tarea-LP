JAVAC=javac
LIB_DIR=lib
JAR=$(LIB_DIR)/json-20240303.jar
SRC_DIR=src
SRC=$(wildcard $(SRC_DIR)/*.java)
MAIN_CLASS=Main

all:
	$(JAVAC) -cp $(SRC_DIR):$(JAR) -d $(SRC_DIR) $(SRC)

run: all
	java -cp $(SRC_DIR):$(JAR) $(MAIN_CLASS)

clean:
	rm -f $(SRC_DIR)/*.class