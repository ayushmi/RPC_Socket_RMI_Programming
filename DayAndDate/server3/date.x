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

program DATEPROG3{
	version SIMP_VERSION {
		int FindDay(date) =1;
	} = 1;
} = 0x5119937;	
