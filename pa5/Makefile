# Vincent Titterton
# vtittert
# 12B
# 3/12/2019
# a makefile dor Dictionary.c

EXEBIN  = DictionaryClient
SOURCES = Dictionary.c 	DictionaryClient.c
OBJECTS = Dictionary.c 	DictionaryClient.o
HEADERS = Dictionary.h
SUBSRC  = README Makefile $(SOURCES) $(HEADERS) DictionaryTest.c
SUBMIT  = submit cmps012b-pt.w19 pa5

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