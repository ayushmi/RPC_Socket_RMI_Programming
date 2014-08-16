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
		if (i==0)
		{
			strcat(message, "id\t----\tIssued?\t----\tBook Name\n");
		}
		if(KMPSearch(Book,temp->name))
		{
			flag=1;
			char str[100];
			sprintf(str,"%d",temp->bookid);
			strcat(message,str);
			strcat(message,"\t----\t");
			if (temp->issued_flag) strcat(message,"YES");
			else strcat(message,"NO");
			strcat(message,"\t----\t");
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
	tempBook->bookid = *nbooks;
	tempBook->next = temp;
	temp = myLibrary;
	printf("Inserted Book %s to library\n",myLibrary->name);
	return(myLibrary);
}
int Issue(int id,char * message,struct library* myLibrary, int *nbooks)
{
	printf("Issuing Book\n");
	int i;
	struct library *temp = myLibrary;
	for (i = 0; i < *nbooks; ++i)
	{
		if (temp->bookid == id)
		{
			if (temp->issued_flag)
			{
				strcpy(message,"ERROR: Issued Failed, book is already issued\n");
			}
			else
			{
				temp->issued_flag = 1;
				strcpy(message,"Book Successfully Issued - Enjoy Reading!!!\n");
			}
		}
		temp = temp->next;
	}
	printf("%s\n",message);
	return(1);	
}
int Renew(int id,char * message,struct library* myLibrary, int *nbooks)
{
	printf("Renewing Book\n");
	int i;
	struct library *temp = myLibrary;
	for (i = 0; i < *nbooks; ++i)
	{
		if (temp->bookid == id)
		{
			if ((temp->reserve_flag))
			{
				strcpy(message,"ERROR: Renewal Failed, book is reserved by someone. Please return the book.\n");
			}
			else
			{
				if (temp->issued_flag)
				{
					strcpy(message,"Book Successfully Renewed - Keep Reading!!!\n");
				}
				else
				{
					strcpy(message,"ERROR: Book Renewal Failed, book is not issued yet\n");
				}
			}
			i = *nbooks;
		}
		temp = temp->next;
	}
	printf("%s\n",message);
	return(1);
}
int Reserve(int id,char * message,struct library* myLibrary, int *nbooks)
{
	printf("Reserving Book\n");
	int i;
	struct library *temp = myLibrary;
	for (i = 0; i < *nbooks; ++i)
	{
		if (temp->bookid == id)
		{
			if (!(temp->issued_flag))
			{
				strcpy(message,"ERROR: Reserve Failed, book is not yet issued. You can issue book.\n");
			}
			else
			{
				temp->reserve_flag += 1;
				strcpy(message,"Book Successfully Reseved - You will get to read it soon!!!\n");
			}
			i = *nbooks;
		}
		temp = temp->next;
	}
	printf("%s\n",message);
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
		fprintf(fp, "%d\n",temp->bookid);
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