/* Author: Ayush Mittal
 * ---------------------
 * CS632A - Assignment
 * ---------------------
 * Second Function
 *  Finds the total days till the given day
 * 	from 1st january 0001.
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
	date  totaldays_1_arg;
	totaldays_1_arg.day = day;
	totaldays_1_arg.month = month;
	totaldays_1_arg.year = year;


	clnt = clnt_create(host, DATEPROG, SIMP_VERSION, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror(host);
		exit(1);
	}

	//Make remote call
	result = totaldays_1(&totaldays_1_arg, clnt);
	if (result == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	clnt_destroy( clnt );

	//print the result
	printf("The Total Number of days till %d/%d/%d is : %d\n",day,month,year,*result);

	return 0;
}


