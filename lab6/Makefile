# Vincent Titterton
# vtittert
# 12M
# 3/4/2019
# a makefile for lab6

# Variables
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = ListInterface.java ListIndexOutOfBoundsException.java List.java \
             ListClient.java
CLASSES    = ListInterface.class ListIndexOutOfBoundsException.class \
             List.class List\$$Node.class ListClient.class
SUBSRC     = $(SOURCES) ListTest.java README Makefile
SUBMIT     = submit cmps012b-pt.w19 lab6

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm -f Manifest $(CLASSES)
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

# Phony Targets
clean:
	rm -f $(JARFILE)

run:
	java -jar $(JARFILE) > my-out

diff:
	diff my-out model-out

submit: $(SUBSRC)
	$(SUBMIT) $(SUBSRC)
