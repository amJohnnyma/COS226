SRC_DIR := src
BIN_DIR := bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

MAIN_CLASS := Main

JAVAC := javac
JAVA := java

.PHONY: all run clean

all: $(CLASSES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(BIN_DIR) $<

run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BIN_DIR)
