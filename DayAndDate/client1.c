/* Author: Ayush Mittal
 * ---------------------
 * CS632A - Assignment
 * ---------------------
 * First Function
 *  Calculates the number of days to account for leap years.
*/

#include<stdio.h>
#include<stdlib.h>
#include "date.h"

int main(int argc, char const *argv[])
{
	char *host;

	if (argc<2)
	{
		printf("usage: %s server_host\n", argv[0]);
		exit(1);
	}
	
	host = argv[1];


	CLIENT *clnt;
	int  *result;
	int day, month, year;

	//Input the date.
	printf("Enter the day, month and the year:");
	scanf("%d%d%d",&day,&month,&year);

	//Prepare the data structure
	date  numberofleapyears_1_arg;
	numberofleapyears_1_arg.day = day;
	numberofleapyears_1_arg.month = month;
	numberofleapyears_1_arg.year = year;

	clnt = clnt_create(host, DATEPROG, SIMP_VERSION, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror(host);
		exit(1);
	}

	//Make the remote call
	result = numberofleapyears_1(&numberofleapyears_1_arg, clnt);
	if (result == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	clnt_destroy( clnt );

	//print the result
	printf("The Number of daysto Account for leap years: %d\n",*result);

	return 0;
}


