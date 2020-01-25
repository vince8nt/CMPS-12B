# Vincent Titterton
# vtittert
# 12M
# 2/18/2019
# Makefile for lab4


JAVASRC    = Dictionary.java DictionaryTest.java DuplicateKeyException.java = KeyNotFoundException.java DictionaryInterface.java DictionaryClient.java
SOURCES    = $(JAVASRC) Makefile README
MAINCLASS  = DictionaryClient
CLASSES    = Dictionary.class DictionaryTest.class DuplicateKeyException.class = KeyNotFoundException.class DictionaryInterface.class DictionaryClient.class
JARFILE = $(MAINCLASS)
SUBMIT = submit cmps012b-pt.w19 lab4
all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
