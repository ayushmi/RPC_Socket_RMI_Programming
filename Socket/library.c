#include <stdio.h>
#include <stdlib.h>
#include "library.h"
#include <string.h>

int KMPSearch(char *pat, char *txt);

void Search(char Book[], char * message,struct library* myLibrary, int *nbooks)
{
	printf("User asked for %s\n",Book);
	int i,flag=0;
	struct library *temp = myLibrary;
	strcpy(message,"Search Result:\n");
	for (i = 0; i < *nbooks; ++i)
	{
		if(KMPSearch(Book,temp->name))
		{
			flag=1;
			strcat(message,temp->name);
			strcat(message,"\n");
		}
		temp = temp->next;
	}
	if (flag==0)
	{
		strcpy(message,"No Such Book Found\n");
	}
	printf("%s\n",message);
}
struct library* Insert(char Book[],struct library* myLibrary, int *nbooks)
{
	struct library *tempBook;
	tempBook = (struct library*)malloc(sizeof(struct library));
	strcpy(tempBook->name, Book);
	tempBook->issued_flag = 0;
	tempBook->reserve_flag=0;
	*nbooks = *nbooks +1;
	struct library *temp = myLibrary;
	myLibrary = tempBook;
	tempBook->next = temp;
	temp = myLibrary;
	printf("Inserted Book %s to library\n",myLibrary->name);
	return(myLibrary);		
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

void computeLPSArray(char *pat, int M, int *lps);
 
int KMPSearch(char *pat, char *txt)
{
    int M = strlen(pat);
    int N = strlen(txt);
 
    // create lps[] that will hold the longest prefix suffix values for pattern
    int *lps = (int *)malloc(sizeof(int)*M);
    int j  = 0;  // index for pat[]
 
    // Preprocess the pattern (calculate lps[] array)
    computeLPSArray(pat, M, lps);
 
    int i = 0;  // index for txt[]
    while(i < N)
    {
      if(pat[j] == txt[i])
      {
        j++;
        i++;
      }
 
      if (j == M)
      {
        //printf("Found pattern at index %d \n", i-j);
        return(1);
        j = lps[j-1];
      }
 
      // mismatch after j matches
      else if(pat[j] != txt[i])
      {
        // Do not match lps[0..lps[j-1]] characters,
        // they will match anyway
        if(j != 0)
         j = lps[j-1];
        else
         i = i+1;
      }
    }
    free(lps); // to avoid memory leak
    return(0);
}
 
void computeLPSArray(char *pat, int M, int *lps)
{
    int len = 0;  // lenght of the previous longest prefix suffix
    int i;
 
    lps[0] = 0; // lps[0] is always 0
    i = 1;
 
    // the loop calculates lps[i] for i = 1 to M-1
    while(i < M)
    {
       if(pat[i] == pat[len])
       {
         len++;
         lps[i] = len;
         i++;
       }
       else // (pat[i] != pat[len])
       {
         if( len != 0 )
         {
           // This is tricky. Consider the example AAACAAAA and i = 7.
           len = lps[len-1];
 
           // Also, note that we do not increment i here
         }
         else // if (len == 0)
         {
           lps[i] = 0;
           i++;
         }
       }
    }
}