/*
* Vincent Titterton
* vtittert
* 12B
* 3/12/2019
* a test for Dictionary.c
*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[])
{
	Dictionary A = newDictionary();
	int i;
	char* word1[] = {"1","2","3","4","5","6","7", "8", "9", "10"};
   	char* word2[] = {"red","green","blue","yellow","orange","purple","brown", "black", "white", "grey"};

   	printf("Empty is %s\n", (isEmpty(A)?"true":"false"));

	for(i=0; i<10; i++)
	{
		insert(A, word1[i], word2[i]);
	}

	printDictionary(stdout, A);
	printf("%d long\n", size(A));

	delete(A, "2");
	delete(A, "4");
	delete(A, "6");
	delete(A, "8");
	delete(A, "10");

	printDictionary(stdout, A);
	printf("%d long\n", size(A));

	for(i=1; i<10; i+=2)
	{
		insert(A, word1[i], word2[i-1]);
	}

	printDictionary(stdout, A);
	printf("%d long\n", size(A));

	printf("Empty is %s\n", (isEmpty(A)?"true":"false"));

	printf("duplicate key error incoming\n");
	insert(A, "6", "black");

	return 0;
}