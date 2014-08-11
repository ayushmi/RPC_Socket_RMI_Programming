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
	date  findday_1_arg;
	findday_1_arg.day = day;
	findday_1_arg.month = month;
	findday_1_arg.year = year;

	clnt = clnt_create(host, DATEPROG, SIMP_VERSION, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror(host);
		exit(1);
	}

	//Make the remote call
	result = findday_1(&findday_1_arg, clnt);
	if (result == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	clnt_destroy( clnt );

	//print the result
	switch(*result)
	{
		case 1: printf("Today is Monday\n"); break;
		case 2: printf("Today is Tuesday\n"); break;
		case 3: printf("Today is Wednesday\n"); break;
		case 4: printf("Today is Thursday\n"); break;
		case 5: printf("Today is Friday\n"); break;
		case 6: printf("Today is Saturday\n"); break;
		case 7: printf("Today is Sunday\n"); break;
	}
	return 0;
}


