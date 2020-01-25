/*
* Vincent Titterton
* vtittert
* 12M
* 2/19/2019
* a dictionary made from a linked list
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[])
{
	if (argc != 2)
	{
		printf("error, format: DictionaryClient (number 1 - 7)\n");
		exit(EXIT_FAILURE);
	}

	Dictionary D = newDictionary();
	
	if (strcmp(argv[1], "1") == 0)
	{
		// tests insert() and printDictionary()
		// dict1, dict2, a apple, dict3, a apple, b banana
		makeEmpty(D);
		printf("dict1\n");
		printDictionary(stdout, D);
		insert(D, "a", "apple");
		printf("dict2\n");
		printDictionary(stdout, D);
		insert(D, "b", "banana");
		printf("dict3\n");
		printDictionary(stdout, D);
	}
	else if (strcmp(argv[1], "2") == 0)
	{
		// tests insert() for duplicate key exception
		// a apple, a apple, a2 apple, cannot insert duplicate keys
		makeEmpty(D);
		insert(D, "a", "apple");
		printDictionary(stdout, D);
		insert(D, "a2", "apple");
		printDictionary(stdout, D);
		insert(D, "a", "pear");
		printDictionary(stdout, D);
	}
	else if (strcmp(argv[1], "3") == 0)
	{
		// tests isEmpty() and makeEmpty()
		// true, false, false, true
		makeEmpty(D);
		printf("%s\n", isEmpty(D) ? "true" : "false");
		insert(D, "a", "apple");
		printf("%s\n", isEmpty(D) ? "true" : "false");
		insert(D, "b", "banana");
		printf("%s\n", isEmpty(D) ? "true" : "false");
		makeEmpty(D);
		printf("%s\n", isEmpty(D) ? "true" : "false");
	}
	else if (strcmp(argv[1], "4") == 0)
	{
		// tests delete()
		// dict1, a apple, b banana, o orange, dict2, b banana, o orange, dict3, b banana, dict4
		makeEmpty(D);
		insert(D, "a", "apple");
		insert(D, "b", "banana");
		insert(D, "o", "orange");
		printf("dict1\n");
		printDictionary(stdout, D);
		delete(D, "a");
		printf("dict2\n");
		printDictionary(stdout, D);
		delete(D, "o");
		printf("dict3\n");
		printDictionary(stdout, D);
		delete(D, "b");
		printf("dict4\n");
		printDictionary(stdout, D);
	}
	else if (strcmp(argv[1], "5") == 0)
	{
		// tests delete() for nonexistent keys
		// cannot delete non-existent key
		makeEmpty(D);
		insert(D, "a", "apple");
		delete(D, "b");
	}
	else if (strcmp(argv[1], "6") == 0)
	{
		// tests lookup()
		// apple, orange
		makeEmpty(D);
		insert(D, "a", "apple");
		printf("%s\n", lookup(D, "a"));
		insert(D, "b", "banana");
		insert(D, "o", "orange");
		printf("%s\n", lookup(D, "o"));
	}
	else if (strcmp(argv[1], "7") == 0)
	{
		// tests size()
		// size is 0, size is 1, size is 2
		makeEmpty(D);
		printf("size is %i\n", size(D));
		insert(D, "a", "apple");
		printf("size is %i\n", size(D));
		insert(D, "b", "banana");
		printf("size is %i\n", size(D));
	}
}

	





