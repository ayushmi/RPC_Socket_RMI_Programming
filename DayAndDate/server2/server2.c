/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "date.h"

int *totaldays_1_svc(date *argp,struct svc_req *rqstp)
{

	int JDN1 = 1721426; //Julian day number for 01/01/0001
	static int  result;

	int day = argp->day;
	int month = argp->month;
	int year = argp->year;
	
	//Calculating JDN for the input date
	long long int a = (14-month)/12;
	long long int y = year + 4800 - a;
	long long int m = month + 12*a  - 3;

	long long int JDN2 = day + (153*m+2)/5 + 365*y + (y/4) - (y/100) + (y/400) - 32045;

	//Calculating final result
	result = JDN2-JDN1+1;

	return(&result);
}
