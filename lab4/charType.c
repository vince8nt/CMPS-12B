/*
* Vincent Titterton
* vtittert
* 12M
* 1/27/2019
* Takes a file as input and creates a file with each
* word from the input reversed and on a new line
*/

#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include <string.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* input, char* a, char* d, char* p, char* w)
{
	int* index;
	index = calloc(4 * sizeof(int), 0);

	for (int i = 0; i < strlen(input); i++)
    {
		if (isalpha((int)input[i]))
		{
			a[index[0]] = input[i];
			index[0]++;
		}

		else if (isalnum((int)input[i]))
		{
			d[index[1]] = input[i];
			index[1]++;
		}

		else if (ispunct((int)input[i]))
		{
			p[index[2]] = input[i];
			index[2]++;
		}

		else if (isspace((int)input[i]))
		{
			w[index[3]] = input[i];
			index[3]++;
		}
	}
	a[index[0]] = '\0';
	d[index[1]] = '\0';
	p[index[2]] = '\0';
	w[index[3]] = '\0';
	free(index);
}


int main(int argc, char* argv[])
{
    FILE* in;       // handle for input file
    FILE* out;      // handle for output file
    char* line;     // string holding input line
    char* a;        // string holding all alpha-numeric chars
    char* d;        // string to hold all digits 0 - 9
    char* p;        // string to hold all punctuation
    char* w;        // string to hold all whitespace chars
    
    // check command line for correct number of arguments
    if( argc != 3 ){
        printf("Usage: %s input-file output-file\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    
    // open input file for reading
    if( (in=fopen(argv[1], "r"))==NULL ){
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    
    // open output file for writing
    if( (out=fopen(argv[2], "w"))==NULL ){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }
    
    // allocate strings line and alpha_num on the heap
    line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
    a = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
    d = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
    p = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
    w = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
    assert( line!=NULL && a!=NULL && d!=NULL && p!=NULL && w!=NULL );
    
    // read each line in input file, extract alpha-numeric characters
    int lineCount = 1;
    while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
        extract_chars(line, a, d, p, w);
        fprintf(out, "line %d contains:\n", lineCount);
        if(strlen(a) == 1)
            fprintf(out, "%d alphabetic character: %s\n", 1, a);
        else
            fprintf(out, "%ld alphabetic characters: %s\n", strlen(a), a);
        if(strlen(d) == 1)
            fprintf(out, "%d numeric character: %s\n", 1, d);
        else
            fprintf(out, "%ld numeric characters: %s\n", strlen(d), d);
        if(strlen(p) == 1)
            fprintf(out, "%d punctuation character: %s\n", 1, p);
        else
            fprintf(out, "%ld punctuation characters: %s\n", strlen(p), p);
        if(strlen(w) == 1)
            fprintf(out, "%d whitespace character: %s\n", 1, w);
        else
            fprintf(out, "%ld whitespace characters: %s\n", strlen(w), w);
        lineCount++;
    }
    
    // free heap memory
    free(line);
    free(a);
    free(d);
    free(p);
    free(w);
    
    // close input and output files
    fclose(in);
    fclose(out);
    
    return EXIT_SUCCESS;
}
