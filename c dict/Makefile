# Vincent Titterton
# vtittert
# 12M
# 2/19/2019
# a Makefile with check utility for lab5

ADT_NAME  = Dictionary
SOURCES   = $(ADT_NAME).c $(ADT_NAME)Client.c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)Client
FLAGS     = -std=c99 -Wall
SUBSRC    = README Makefile $(ADT_NAME)Test.c $(ADT_NAME).h $(SOURCES)
SUBMIT    = submit cmps012b-pt.w19 lab5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)

submit: $(SUBSRC)
	$(SUBMIT) $(SUBSRC)