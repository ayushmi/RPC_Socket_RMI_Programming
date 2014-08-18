/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "date2.h"

int *
numberof_1_svc(argp, rqstp)
	date2 *argp;
	struct svc_req *rqstp;
{

	static int result;

	int day1 = argp->day1;
	int month1 = argp->month1;
	int year1 = argp->year1;
	int day2 = argp->day2;
	int month2 = argp->month2;
	int year2 = argp->year2;

	printf("%d/%d/%d to %d/%d/%d\n",day1,month1,year1,day2,month2,year2);

	int requestedDay = argp->requestedDay;

	long long int a1 = (14-month1)/12;
	long long int y1 = year1 + 4800 - a1;
	long long int m1 = month1 + 12*a1  - 3;
	long long int JDN1 = day1 + (153*m1+2)/5 + 365*y1 + (y1/4) - (y1/100) + (y1/400) - 32045;
	
	int dayondate1 = (JDN1-1721426+1)%7;
	printf("Day on %d/%d/%d : %d \n",day1, month1,year1,dayondate1);

	switch(dayondate1)
	{
		case 1: result=-6; break;
		case 2: result=-5; break;
		case 3: result=-4; break;
		case 4: result=-3; break;
		case 5: result=-2; break;
		case 6: result=-1; break;
		case 0: result=-0; break;
	}	

	long long int a2 = (14-month2)/12;
	long long int y2 = year2 + 4800 - a2;
	long long int m2 = month2 + 12*a2  - 3;
	long long int JDN2 = day2 + (153*m2+2)/5 + 365*y2 + (y2/4) - (y2/100) + (y2/400) - 32045;

	int dayondate2 = (JDN2-1721426+1)%7;
	printf("Day on %d/%d/%d : %d \n",day2, month2,year2,dayondate2);
	
	switch(dayondate2)
	{
		case 1: result=result-1; break;
		case 2: result=result-2; break;
		case 3: result=result-3; break;
		case 4: result=result-4; break;
		case 5: result=result-5; break;
		case 6: result=result-6; break;
		case 0: result=result-0; break;
	}

	result = result+(JDN2-JDN1+1);
	result = result/7;	
	if(requestedDay >= dayondate1) result = result+1;
	if(requestedDay <= dayondate2) result = result+1;
	return(&result);
}
