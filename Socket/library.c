#include <stdio.h>
#include <stdlib.h>
#include "library.h"
#include <string.h>

void Search(char Book[], char * message,struct library* myLibrary, int *nbooks)
{
	printf("User asked for %s\n",Book);
}
int Insert(char Book[],struct library* myLibrary, int *nbooks)
{
	struct library *tempBook;
	tempBook = malloc(sizeof(struct library));
	strcpy(tempBook->name, Book);
	tempBook->issued_flag = 0;
	tempBook->reserve_flag=0;
	return(1);		
}
int Issue(char Book[],struct library* myLibrary, int *nbooks)
{
	return(1);	
}
int Renew(char Book[],struct library* myLibrary, int *nbooks)
{
	return(1);
}
int Reserve(char Book[],struct library* myLibrary, int *nbooks)
{
	return(1);
}
int Exit(struct library* myLibrary, int *nbooks)
{
	FILE *fp;
	fp = fopen("data","w");
	fprintf(fp, "%d\n",*nbooks);
	int i=0;
	struct library *temp=myLibrary;
	for (i = 0; i < *nbooks; ++i)
	{
		fprintf(fp, "%s\n",temp->name);
		fprintf(fp, "%d\n",temp->issued_flag);
		fprintf(fp, "%d\n",temp->reserve_flag);
		temp = temp->next;
	}
	fclose(fp);
	return(1);
}