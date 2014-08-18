/* Author: Ayush Mittal
 * ---------------------
 * CS632A - Assignment
 * ---------------------
 * Protocol Specification
*/

struct date{
	int day;
	int month;
	int year;
};

program DATEPROG2{
	version SIMP_VERSION {
		int TotalDays(date) = 1;
	} = 1;
} = 0x5119936;	
