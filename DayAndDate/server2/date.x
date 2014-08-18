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

program DATEPROG{
	version SIMP_VERSION {
		int TotalDays(date) = 1;
	} = 1;
} = 0x5119936;	
