
SRC_DIR := src
BIN_DIR := bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

MAIN_CLASS := Main

ARGS :=

JAVAC := javac
JAVA := java

.PHONY: all run clean

all: 
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)


run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS) $(ARGS)

clean:
	rm -rf $(BIN_DIR)

