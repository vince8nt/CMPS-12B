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
#define tableSize 101

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

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input)
{
	unsigned int result = 0xBAE86554;
	while (*input)
	{
		result ^= *input++;
		result = rotate_left(result, 5);
	}
	return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key)
{
	return pre_hash(key)%tableSize;
}

// DictionaryObj
typedef struct DictionaryObj
{
	int size;
	struct NodeObj* table[tableSize];
}
DictionaryObj;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void)
{
	int i;
	Dictionary D = malloc(sizeof(DictionaryObj));

	assert(D != NULL);
	D->size = 0;
	for (i = 0; i < tableSize; i++)
	{
		D->table[i] = NULL;
	}
	return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD)
{
	if (pD != NULL && *pD != NULL)
	{
		if(!isEmpty(*pD))
			makeEmpty(*pD);
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
	return(D->size == 0);
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
	return(D->size);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* key)
{
	Node n;

	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	n = D->table[hash(key)];
	while(n != NULL && strcmp(n->key, key) != 0)
	{
		n = n->next;
	}
	if (n == NULL)
		return (NULL);
	return (n->value);
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* key, char* value)
{
	Node n;
	Node head;
	int i;

	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	if(lookup(D, key) != NULL)
	{
		fprintf(stderr, "cannot insert duplicate keys\n");
		exit(EXIT_FAILURE);
	}
	i = hash(key);
	n = newNode(key, value);
	n->next = D->table[i];
	D->table[i] = n;

	D->size++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, key)!=NULL
void delete(Dictionary D, char* key)
{
	Node n, w;
	int i;

	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	if(lookup(D, key) == NULL)
	{
		fprintf(stderr, "cannot delete non-existent key\n");
		exit(EXIT_FAILURE);
	}
	i = hash(key);
	n = D->table[i];
	if (strcmp(n->key, key) == 0)
	{
		D->table[i] = D->table[i]->next;
		freeNode(&n);
	}
	else
	{
		while(strcmp(n->next->key, key) != 0)
		{
			n = n->next;
		}
		w = n->next;
		n->next = w->next;
		freeNode(&w);
	}
	D->size--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
	Node n;
	int i;

	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for (i = 0; i < tableSize; i++)
	{
		while (D->table[i] != NULL)
		{
			n = D->table[i];
			D->table[i] = D->table[i]->next;
			freeNode(&n);
		}
	}
	D->size = 0;
}

// printDictionary()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D)
{
	Node n;
	int i;

	if( D==NULL )
	{
		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for (i = 0; i < tableSize; i++)
	{
		n = D->table[i];
		while (n != NULL)
		{
			fprintf(out, "%s %s\n", n->key, n->value);
			n = n->next;
		}
	}
}










