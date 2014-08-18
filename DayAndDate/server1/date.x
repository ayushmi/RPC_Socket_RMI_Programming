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
		int NumberOfLeapYears(date) = 1;
	} = 1;
} = 0x5119935;	
