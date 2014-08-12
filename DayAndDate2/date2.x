/* Author: Ayush Mittal
 * ---------------------
 * CS632A - Assignment
 * ---------------------
 * Protocol Specification
*/

struct date2{
	int day1;
	int month1;
	int year1;
	int day2;
	int month2;
	int year2;
	int requestedDay;
};

program DATEPROG{
	version SIMP_VERSION {
		int NumberOf(date2) = 1;
	} = 1;
} = 0x5119932;