# Vincent Titterton
# vtittert
# 12M
# 1/29/2019
# Makefile for FileReverse.c with macros

SRC = FileReverse.c
FLAGS = -std=c99 -Wall
SOURCES = README Makefile $(SRC)
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.w19 lab3

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit : $(SOURCES)
	$(SUBMIT) $(SOURCES)
