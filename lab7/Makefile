# Vincent Titterton
# vtittert
# 12M
# 3/6/2019
# Makefile for lab7

JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT = submit cmps012b-pt.w19 lab7

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

test:
	curl https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa3/pa3.sh > pa3.sh
	chmod +x pa3.sh
	./pa3.sh
	rm -rf pa3.sh backup
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
