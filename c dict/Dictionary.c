/*
* Vincent Titterton
* vtittert
* 12B
* 3/12/2019
* a dictionary made from a hash map
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj
{
	char* key;
	char* value;
	struct NodeObj* next;
}
NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* key, char* value)
{
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N->key = key;
	N->value = value;
	N->next = NULL;
	return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN)
{
	if( pN!=NULL && *pN!=NULL )
	{
		free(*pN);
		*pN = NULL;
	}
}

// DictionaryObj
typedef struct DictionaryObj
{
	int size;
	int tableSize;
	Node table[];
}
DictionaryObj;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift)
{
	int sizeInBits = 8*sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if ( shift == 0 )
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void)
{
	int i;

	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	D -> size = 0;
	D -> tableSize = 101;
	D -> table = malloc(tableSize * sizeof(Node));
	for (i = 0; i < tableSize; i++)
	{
		table[i] = NULL;
	}
	return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD)
{
	Node n;
	int i;

	if (pD != NULL && *pD != NULL)
	{
		for (i = 0; i < pD -> tableSize; i++)
		{
			while (table[i] != NULL)
			{
				n = pD -> table[i];
				pD -> table[i] = pD -> table[i] -> next;
				free(n);
			}
		}
		free(pD -> table);
		free(*pD);
		*pD = NULL;
	}
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D)
{
	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return(D -> size == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D)
{
	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return(D -> size);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* key)
{
	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
}




