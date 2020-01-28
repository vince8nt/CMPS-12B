# Vincent Titterton
# vtittert
# 12b
# 3/2/2019
# a Makefile using wildcards for pa4

JAVAC      = javac 
MAINCLASS  = Simulation
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)
SUBMIT     = submit cmps012b-pt.w19 pa4 


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm *.class $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

test: 
	curl https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa4/pa4.sh > pa4.sh
	chmod +x pa4.sh
	./pa4.sh
	rm -rf pa4.sh backup
