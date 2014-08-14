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
		int TotalDays(date) = 2;
		int FindDay(date) =3;
	} = 1;
} = 0x5119935;	